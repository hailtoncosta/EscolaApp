package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Professor;
import com.app.minhaescolaapp.repository.ProfessorRepository;
import com.app.minhaescolaapp.service.ProfessorService;


@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping("/professor/cadastrarprofessor")
	public ModelAndView iniciarProfessor() {
		ModelAndView modelAndView = new ModelAndView("professor/cadastrarprofessor");
		modelAndView.addObject("objprofessores", new Professor());
		return modelAndView;
	}
	
	@PostMapping("/professor/salvarprofessor")
	public ModelAndView salvarProfessor(Professor professor) {
		professorService.salvarProfessor(professor);
		ModelAndView modelAndView = new ModelAndView("professor/listarprofessores");
		modelAndView.addObject("listarprofessores", professorService.listarProfessores());
		return modelAndView;
	}
	
	@GetMapping("/professor/listarprofessores")
	public ModelAndView listarProfessor() {
		ModelAndView modelAndView = new ModelAndView("professor/listarprofessores");
		modelAndView.addObject("listarprofessores", professorService.listarProfessores());
		return modelAndView;
	}
	
	@GetMapping("/professor/editarprofessor/{id}")
	public ModelAndView editarProfessor(@PathVariable("id") Long id) {
		Optional<Professor> professores = professorRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("professor/cadastrarprofessor");
		modelAndView.addObject("objprofessores", professores.get());
		return modelAndView;
	}
	
	@GetMapping("/professor/deletarprofessor/{id}")
	public ModelAndView deletarProfessor(@PathVariable("id") Long id) {
		professorService.deletarProfessor(id);
		return new ModelAndView("redirect:/professor/listarprofessores");
	}
	
	
}
