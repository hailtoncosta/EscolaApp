package com.app.minhaescolaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.minhaescolaapp.model.Secretario;
import com.app.minhaescolaapp.repository.SecretarioRepository;

@Service
public class SecretarioService {
	
	@Autowired
	private SecretarioRepository secretarioRepository;
	
	public void salvarSecretario(Secretario secretario) {
		secretarioRepository.save(secretario);
	}
	
	public void deletarSecretario(@PathVariable("id") Long id) {
		secretarioRepository.deleteById(id);
	}
	
	public List<Secretario> listarSecretarios() {
		List<Secretario> secretarios = secretarioRepository.findAll();
		return secretarios;
	}

}
