package com.app.minhaescolaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecretarioController {
	
	@GetMapping("/secretario/cadastrarsecretario")
	public ModelAndView iniciarProfessor() {
		ModelAndView modelAndView = new ModelAndView("secretario/cadastrarsecretario");
		return modelAndView;
	}
	
	@GetMapping("/secretario/listarsecretarios")
	public ModelAndView listarProfessor() {
		ModelAndView modelAndView = new ModelAndView("secretario/listarsecretarios");
		return modelAndView;
	}

}
