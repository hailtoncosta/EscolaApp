package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		modelAndView.addObject("listartesoureiros", tesoureiroService.listarTesoureiros());
		return modelAndView;
	}
	
	@GetMapping("/tesoureiro/listartesoureiros")
	public ModelAndView listarTesoureiro() {
		ModelAndView modelAndView = new ModelAndView("tesoureiro/listartesoureiros");
		modelAndView.addObject("listartesoureiros", tesoureiroService.listarTesoureiros());
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
	
	
}
