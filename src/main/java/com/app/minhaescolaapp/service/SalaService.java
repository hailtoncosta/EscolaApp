package com.app.minhaescolaapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Sala;
import com.app.minhaescolaapp.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public Sala salvarSala(Sala sala) {
		return salaRepository.save(sala);
	}
	
	public void deletarSala(@PathVariable("id") Long id) {
		salaRepository.deleteById(id);
	}
	
	public void editarSala(@PathVariable("id") Long id, ModelAndView modelAndView) {
		Optional<Sala> salas = salaRepository.findById(id);
		modelAndView.addObject("editarsala", salas.get());
	}
}
