package com.app.minhaescolaapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.minhaescolaapp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.login = ?1")
	Usuario userFindByLogin(String login);

	@Query("select u from Usuario u where u.login like %?1%")
	List<Usuario> buscarUsuarioPorNome(String login);

	default Page<Usuario> findUsuariosByNamePage(String login, Pageable pageable) {

		Usuario usuario = new Usuario();
		usuario.setLogin(login);

		// Estamos configurando a pesquisa para consultar por partes do nome no banco de
		// dados, igual ao Like do SQL
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("login",
				ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		// Une o objeto com o valor e a configuração para consultar
		Example<Usuario> example = Example.of(usuario, exampleMatcher);

		Page<Usuario> usuarios = findAll(example, pageable);

		return usuarios;
	}

}
