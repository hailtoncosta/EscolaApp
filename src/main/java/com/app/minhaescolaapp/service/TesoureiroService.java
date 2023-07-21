package com.app.minhaescolaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.minhaescolaapp.model.Secretario;
import com.app.minhaescolaapp.model.Tesoureiro;
import com.app.minhaescolaapp.repository.TesoureiroRepository;

@Service
public class TesoureiroService {
	
	@Autowired
	private TesoureiroRepository tesoureiroRepository;
	
	public void salvarTesoureiro(Tesoureiro tesoureiro) {
		tesoureiroRepository.save(tesoureiro);
	}
	
	public void deletarTesoureiro(@PathVariable("id") Long id) {
		tesoureiroRepository.deleteById(id);
	}
	
	public List<Tesoureiro> listarTesoureiros() {
		List<Tesoureiro> tesoureiros = tesoureiroRepository.findAll();
		return tesoureiros;
	}

}
