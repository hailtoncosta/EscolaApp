package com.app.minhaescolaapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Disciplina;
import com.app.minhaescolaapp.model.Usuario;
import com.app.minhaescolaapp.repository.DisciplinaRepository;
import com.app.minhaescolaapp.repository.UsuarioRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public Disciplina salvarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	public void deletarDisciplina(@PathVariable("id") Long id) {
		disciplinaRepository.deleteById(id);
	}
	
	public void editarDisciplina(@PathVariable("id") Long id, ModelAndView modelAndView) {
		Optional<Disciplina> disciplinas = disciplinaRepository.findById(id);
		modelAndView.addObject("editardisciplina", disciplinas.get());
	}
}
