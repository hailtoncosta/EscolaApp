package com.app.minhaescolaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.minhaescolaapp.model.Funcionario;
import com.app.minhaescolaapp.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void salvarFuncionario(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	public void deletarFuncionario(@PathVariable("id") Long id) {
		funcionarioRepository.deleteById(id);
	}
	
	public List<Funcionario> listarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return funcionarios;
	}

}
