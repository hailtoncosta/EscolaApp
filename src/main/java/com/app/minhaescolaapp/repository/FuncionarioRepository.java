package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Query("select f from Funcionario f where f.nome like %?1%")
	List<Funcionario> buscarFuncionarioPorNome(String nome);

	default Page<Funcionario> findFuncionariosByNamePage(String nome, Pageable pageable) {

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);;

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Funcionario> example = Example.of(funcionario, exampleMatcher);

		Page<Funcionario> funcionarios = findAll(example, pageable);

		return funcionarios;
	}

}
