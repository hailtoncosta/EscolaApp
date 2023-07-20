package com.app.minhaescolaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.minhaescolaapp.model.Professor;
import com.app.minhaescolaapp.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public void salvarProfessor(Professor professor) {
		professorRepository.save(professor);
	}
	
	public void deletarProfessor(@PathVariable("id") Long id) {
		professorRepository.deleteById(id);
	}
	
	public List<Professor> listarProfessores() {
		List<Professor> professores = professorRepository.findAll();
		return professores;
	}

}
