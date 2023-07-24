package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Secretario;

public interface SecretarioRepository extends JpaRepository<Secretario, Long>{
	
	
	@Query("select u from Secretario u where u.nome like %?1%")
	List<Secretario> buscarSecretarioPorNome(String nome);

	default Page<Secretario> findSecretariosByNamePage(String nome, Pageable pageable) {

		Secretario secretario = new Secretario();
		secretario.setNome(nome);;

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Secretario> example = Example.of(secretario, exampleMatcher);

		Page<Secretario> secretarios = findAll(example, pageable);

		return secretarios;
	}

}
