package com.app.minhaescolaapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.minhaescolaapp.model.Aluno;

@Repository
@Transactional
public interface EscolaRepository extends JpaRepository<Aluno, Long>{
	
	@Query("select a from Aluno a where a.login = ?1")
	Aluno userFindByLogin(String login);
	
}
