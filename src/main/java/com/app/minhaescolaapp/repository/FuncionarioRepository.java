package com.app.minhaescolaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.minhaescolaapp.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
