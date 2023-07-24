package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	
	@Query("select d from Disciplina d where d.discricaoDisciplina like %?1%")
	List<Disciplina> buscarFuncionarioPorNome(String discricaoDisciplina);

	default Page<Disciplina> findDisciplinaByNamePage(String discricaoDisciplina, Pageable pageable) {

		Disciplina disciplina = new Disciplina();
		disciplina.setDiscricaoDisciplina(discricaoDisciplina);

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("discricaoDisciplina",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Disciplina> example = Example.of(disciplina, exampleMatcher);

		Page<Disciplina> disciplinas = findAll(example, pageable);

		return disciplinas;
	}

}
