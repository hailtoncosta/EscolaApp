package com.app.minhaescolaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
	
	@GetMapping("/")
	public ModelAndView acessarPrincipal() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@GetMapping("/index")
	public ModelAndView acessarIndex() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	@GetMapping("/errorpage/errorpage")
	public ModelAndView acessarErrorPage() {
		ModelAndView modelAndView = new ModelAndView("errorpage/errorpage");
		return modelAndView;
	}
}
