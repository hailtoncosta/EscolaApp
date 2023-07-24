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

import com.app.minhaescolaapp.model.Sala;
import com.app.minhaescolaapp.repository.SalaRepository;
import com.app.minhaescolaapp.service.SalaService;

@Controller
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@GetMapping("/sala/cadastrarsala")
	public ModelAndView cadastrarSalas() {
		ModelAndView modelAndView = new ModelAndView("sala/cadastrarsala");
		modelAndView.addObject("objsala", new Sala());
		return modelAndView;
	}
	
	@PostMapping("/salvarsala")
	public ModelAndView salvarSala(Sala sala) {
		salaService.salvarSala(sala);
		return new ModelAndView("redirect:/sala/listarsalas");
	}
	
	@GetMapping("/sala/listarsalas")
	public ModelAndView listarSalas() {
		ModelAndView modelAndView = new ModelAndView("sala/listarsalas");
		modelAndView.addObject("listarsalas", salaRepository.findAll(PageRequest.of(0, 5, Sort.by("descricaoSala"))));
		return modelAndView;
	}
	
	@GetMapping("/sala/deletarsala/{id}")
	public ModelAndView deletarSala(@PathVariable("id") Long id) {
		salaService.deletarSala(id);
		return new ModelAndView("redirect:/sala/listarsalas");
	}
	
	@GetMapping("/sala/editarsala/{id}")
	public ModelAndView editarSala(@PathVariable("id") Long id) {
		Optional<Sala> salas = salaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("sala/cadastrarsala");
		modelAndView.addObject("objsala", salas.get());
		return modelAndView;
	}
	
	@GetMapping("/salaspage")
	public ModelAndView carregaSalaPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Sala> pageSalas = salaRepository.findAll(pageable);
		modelAndView.addObject("listarsalas", pageSalas);
		modelAndView.addObject("objsala", new Sala());
		modelAndView.setViewName("/sala/listarsalas");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisarsala")
	public ModelAndView pesquisarSala(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"descricaoSala"}) Pageable pageable) {
		
		Page<Sala> salas = null;
		
		salas = salaRepository.findSalaByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("sala/listarsalas");
		modelAndView.addObject("listarsalas", salas);
		modelAndView.addObject("objsala", new Sala());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
}
