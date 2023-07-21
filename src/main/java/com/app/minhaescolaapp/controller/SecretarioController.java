package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Secretario;
import com.app.minhaescolaapp.repository.SecretarioRepository;
import com.app.minhaescolaapp.service.SecretarioService;


@Controller
public class SecretarioController {
	
	@Autowired
	private SecretarioService secretarioService;
	
	@Autowired
	private SecretarioRepository secretarioRepository;
	
	@GetMapping("/secretario/cadastrarsecretario")
	public ModelAndView iniciarSecretario() {
		ModelAndView modelAndView = new ModelAndView("secretario/cadastrarsecretario");
		modelAndView.addObject("objsecretarios", new Secretario());
		return modelAndView;
	}
	
	@PostMapping("/salvarsecretario")
	public ModelAndView salvarSecretario(Secretario secretario) {
		secretarioService.salvarSecretario(secretario);
		ModelAndView modelAndView = new ModelAndView("secretario/listarsecretarios");
		modelAndView.addObject("listarsecretarios", secretarioService.listarSecretarios());
		return modelAndView;
	}
	
	@GetMapping("/secretario/listarsecretarios")
	public ModelAndView listarProfessor() {
		ModelAndView modelAndView = new ModelAndView("secretario/listarsecretarios");
		modelAndView.addObject("listarsecretarios", secretarioService.listarSecretarios());
		return modelAndView;
	}
	
	@GetMapping("/secretario/editarsecretario/{id}")
	public ModelAndView editarProfessor(@PathVariable("id") Long id) {
		Optional<Secretario> secretarios = secretarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("secretario/cadastrarsecretario");
		modelAndView.addObject("objsecretarios", secretarios.get());
		return modelAndView;
	}
	
	@GetMapping("/secretario/deletarsecretario/{id}")
	public ModelAndView deletarSecretario(@PathVariable("id") Long id) {
		secretarioService.deletarSecretario(id);
		return new ModelAndView("redirect:/secretario/listarsecretarios");
	}
	
	
}
