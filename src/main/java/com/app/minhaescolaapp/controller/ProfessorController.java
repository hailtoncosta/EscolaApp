package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Professor;
import com.app.minhaescolaapp.model.Secretario;
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
		modelAndView.addObject("listarprofessores", professorRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		return modelAndView;
	}
	
	@GetMapping("/professor/listarprofessores")
	public ModelAndView listarProfessor() {
		ModelAndView modelAndView = new ModelAndView("professor/listarprofessores");
		modelAndView.addObject("listarprofessores", professorRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
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
	
	@GetMapping("/professorespage")
	public ModelAndView carregaProfessoresPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Professor> pageProfessor = professorRepository.findAll(pageable);
		modelAndView.addObject("listarprofessores", pageProfessor);
		modelAndView.addObject("objprofessores", new Secretario());
		modelAndView.setViewName("professor/listarprofessores");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisarprofessor")
	public ModelAndView pesquisarSecretario(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"nome"}) Pageable pageable) {
		
		Page<Professor> professores = null;
		
		professores = professorRepository.findProfessoresByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("professor/listarprofessores");
		modelAndView.addObject("listarprofessores", professores);
		modelAndView.addObject("objprofessores", new Secretario());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
	
	
}
