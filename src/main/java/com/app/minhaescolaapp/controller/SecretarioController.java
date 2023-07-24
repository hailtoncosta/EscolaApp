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
import com.app.minhaescolaapp.model.Usuario;
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
		modelAndView.addObject("listarsecretarios", secretarioRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
		return modelAndView;
	}
	
	@GetMapping("/secretario/listarsecretarios")
	public ModelAndView listarProfessor() {
		ModelAndView modelAndView = new ModelAndView("secretario/listarsecretarios");
		modelAndView.addObject("listarsecretarios", secretarioRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
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
	
	@GetMapping("/secretariospage")
	public ModelAndView carregaSecretariosPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Secretario> pageSecretario = secretarioRepository.findAll(pageable);
		modelAndView.addObject("listarsecretarios", pageSecretario);
		modelAndView.addObject("objsecretarios", new Secretario());
		modelAndView.setViewName("/secretario/listarsecretarios");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisarsecretario")
	public ModelAndView pesquisarSecretario(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"nome"}) Pageable pageable) {
		
		Page<Secretario> secretarios = null;
		
		secretarios = secretarioRepository.findSecretariosByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("secretario/listarsecretarios");
		modelAndView.addObject("listarsecretarios", secretarios);
		modelAndView.addObject("objsecretarios", new Secretario());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
	
	
}
