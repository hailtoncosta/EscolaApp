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

import com.app.minhaescolaapp.model.Secretario;
import com.app.minhaescolaapp.model.Tesoureiro;
import com.app.minhaescolaapp.repository.TesoureiroRepository;
import com.app.minhaescolaapp.service.TesoureiroService;


@Controller
public class TesoureiroController {
	
	@Autowired
	private TesoureiroService tesoureiroService;
	
	@Autowired
	private TesoureiroRepository tesoureiroRepository;
	
	@GetMapping("/tesoureiro/cadastrartesoureiro")
	public ModelAndView iniciarTesoureiro() {
		ModelAndView modelAndView = new ModelAndView("tesoureiro/cadastrartesoureiro");
		modelAndView.addObject("objtesoureiros", new Tesoureiro());
		return modelAndView;
	}
	
	@PostMapping("/salvartesoureiro")
	public ModelAndView salvarTesoureiro(Tesoureiro tesoureiro) {
		tesoureiroService.salvarTesoureiro(tesoureiro);
		ModelAndView modelAndView = new ModelAndView("tesoureiro/listartesoureiros");
		modelAndView.addObject("listartesoureiros", tesoureiroRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		return modelAndView;
	}
	
	@GetMapping("/tesoureiro/listartesoureiros")
	public ModelAndView listarTesoureiro() {
		ModelAndView modelAndView = new ModelAndView("tesoureiro/listartesoureiros");
		modelAndView.addObject("listartesoureiros", tesoureiroRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		return modelAndView;
	}
	
	@GetMapping("/tesoureiro/editartesoureiro/{id}")
	public ModelAndView editarTesoureiro(@PathVariable("id") Long id) {
		Optional<Tesoureiro> tesoureiros = tesoureiroRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("tesoureiro/cadastrartesoureiro");
		modelAndView.addObject("objtesoureiros", tesoureiros.get());
		return modelAndView;
	}
	
	@GetMapping("/tesoureiro/deletartesoureiro/{id}")
	public ModelAndView deletarSecretario(@PathVariable("id") Long id) {
		tesoureiroService.deletarTesoureiro(id);
		return new ModelAndView("redirect:/tesoureiro/listartesoureiros");
	}
	
	@GetMapping("/tesoureirospage")
	public ModelAndView carregaTesoureirosPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Tesoureiro> pageTesoureiros = tesoureiroRepository.findAll(pageable);
		modelAndView.addObject("listartesoureiros", pageTesoureiros);
		modelAndView.addObject("objtesoureiros", new Tesoureiro());
		modelAndView.setViewName("/tesoureiro/listartesoureiros");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisartesoureiro")
	public ModelAndView pesquisarTesoureiro(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"nome"}) Pageable pageable) {
		
		Page<Tesoureiro> tesoureiros = null;
		
		tesoureiros = tesoureiroRepository.findTesoureirosByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("tesoureiro/listartesoureiros");
		modelAndView.addObject("listartesoureiros", tesoureiros);
		modelAndView.addObject("objtesoureiros", new Secretario());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
	
	
}
