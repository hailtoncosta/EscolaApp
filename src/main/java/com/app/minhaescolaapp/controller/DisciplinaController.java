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

import com.app.minhaescolaapp.model.Disciplina;
import com.app.minhaescolaapp.model.Usuario;
import com.app.minhaescolaapp.repository.DisciplinaRepository;
import com.app.minhaescolaapp.service.DisciplinaService;

@Controller
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@GetMapping("/disciplina/cadastrardisciplinas")
	public ModelAndView cadastrarDisciplinas() {
		ModelAndView modelAndView = new ModelAndView("disciplina/cadastrardisciplinas");
		modelAndView.addObject("objdisciplina", new Disciplina());
		return modelAndView;
	}
	
	@PostMapping("/salvardisciplina")
	public ModelAndView salvarDisciplina(Disciplina disciplina) {
		disciplinaService.salvarDisciplina(disciplina);
		return new ModelAndView("redirect:/disciplina/listardisciplinas");
	}
	
	@GetMapping("/disciplina/listardisciplinas")
	public ModelAndView listarDisciplinas() {
		ModelAndView modelAndView = new ModelAndView("disciplina/listardisciplinas");
		modelAndView.addObject("listardisciplinas", disciplinaRepository.findAll(PageRequest.of(0, 5, Sort.by("discricaoDisciplina"))));
		return modelAndView;
	}
	
	@GetMapping("/disciplina/deletardisciplina/{id}")
	public ModelAndView deletarDisciplina(@PathVariable("id") Long id) {
		disciplinaService.deletarDisciplina(id);
		return new ModelAndView("redirect:/disciplina/listardisciplinas");
	}
	
	@GetMapping("/disciplina/editardisciplina/{id}")
	public ModelAndView editarDisciplina(@PathVariable("id") Long id) {
		Optional<Disciplina> disciplinas = disciplinaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("disciplina/cadastrardisciplinas");
		modelAndView.addObject("objdisciplina", disciplinas.get());
		return modelAndView;
	}
	
	@GetMapping("/disciplinaspage")
	public ModelAndView carregaDisciplinasPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Disciplina> pageDisciplinas = disciplinaRepository.findAll(pageable);
		modelAndView.addObject("listardisciplinas", pageDisciplinas);
		modelAndView.addObject("objdisciplina", new Disciplina());
		modelAndView.setViewName("/disciplina/listardisciplinas");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisardisciplina")
	public ModelAndView pesquisarDisciplina(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"discricaoDisciplina"}) Pageable pageable) {
		
		Page<Disciplina> disciplinas = null;
		
		disciplinas = disciplinaRepository.findDisciplinaByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("disciplina/listardisciplinas");
		modelAndView.addObject("listardisciplinas", disciplinas);
		modelAndView.addObject("objdisciplina", new Usuario());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
}
