package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Funcionario;
import com.app.minhaescolaapp.repository.FuncionarioRepository;
import com.app.minhaescolaapp.service.FuncionarioService;


@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/funcionario/cadastrarfuncionario")
	public ModelAndView iniciarFuncionario() {
		ModelAndView modelAndView = new ModelAndView("funcionario/cadastrarfuncionario");
		modelAndView.addObject("objfuncionarios", new Funcionario());
		return modelAndView;
	}
	
	@PostMapping("/salvarfuncionario")
	public ModelAndView salvarFuncionario(Funcionario funcionario) {
		funcionarioService.salvarFuncionario(funcionario);
		ModelAndView modelAndView = new ModelAndView("funcionario/listarfuncionarios");
		modelAndView.addObject("listarfuncionarios", funcionarioService.listarFuncionarios());
		return modelAndView;
	}
	
	@GetMapping("/funcionario/listarfuncionarios")
	public ModelAndView listarFuncionario() {
		ModelAndView modelAndView = new ModelAndView("funcionario/listarfuncionarios");
		modelAndView.addObject("listarfuncionarios", funcionarioService.listarFuncionarios());
		return modelAndView;
	}
	
	@GetMapping("/funcionario/editarfuncionario/{id}")
	public ModelAndView editarFuncionario(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionarios = funcionarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("funcionario/cadastrarfuncionario");
		modelAndView.addObject("objfuncionarios", funcionarios.get());
		return modelAndView;
	}
	
	@GetMapping("/funcionario/deletarfuncionario/{id}")
	public ModelAndView deletarFuncionario(@PathVariable("id") Long id) {
		funcionarioService.deletarFuncionario(id);
		return new ModelAndView("redirect:/funcionario/listarfuncionarios");
	}
	
	
}
