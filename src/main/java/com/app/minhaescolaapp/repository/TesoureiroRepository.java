package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Secretario;
import com.app.minhaescolaapp.model.Tesoureiro;

public interface TesoureiroRepository extends JpaRepository<Tesoureiro, Long>{
	
	@Query("select t from Tesoureiro t where t.nome like %?1%")
	List<Tesoureiro> buscarTesoureiroPorNome(String nome);

	default Page<Tesoureiro> findTesoureirosByNamePage(String nome, Pageable pageable) {

		Tesoureiro tesoureiro = new Tesoureiro();
		tesoureiro.setNome(nome);;

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Tesoureiro> example = Example.of(tesoureiro, exampleMatcher);

		Page<Tesoureiro> tesoureiros = findAll(example, pageable);

		return tesoureiros;
	}

}
