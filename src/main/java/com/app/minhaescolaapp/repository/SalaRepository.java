package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	
	@Query("select s from Sala s where s.descricaoSala like %?1%")
	List<Sala> buscarFuncionarioPorNome(String descricaoSala);

	default Page<Sala> findSalaByNamePage(String discricaoSala, Pageable pageable) {

		Sala sala = new Sala();
		sala.setDescricaoSala(discricaoSala);

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("descricaoSala",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Sala> example = Example.of(sala, exampleMatcher);

		Page<Sala> salas = findAll(example, pageable);

		return salas;
	}

}
