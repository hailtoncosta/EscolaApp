package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Usuario;
import com.app.minhaescolaapp.repository.UsuarioRepository;

@Controller
public class AlunoController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/cadastro/cadastroaluno")
	public ModelAndView acessarCadastro(Usuario aluno) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		modelAndView.addObject("objaluno", new Usuario());
		modelAndView.addObject("listaalunos", usuarioRepository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/cadastro/salvaraluno")
	public ModelAndView salvarAluno(Usuario aluno) {
			
			aluno.setSenha(new BCryptPasswordEncoder().encode(aluno.getSenha()));
			usuarioRepository.save(aluno);
			
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			modelAndView.addObject("objaluno", new Usuario());
			modelAndView.addObject("listaalunos", usuarioRepository.findAll());
			return modelAndView;
	}
	
	@PostMapping("/errorpage/salvaraluno")
	public ModelAndView salvarErrorPage(Usuario aluno) {
			
			aluno.setSenha(new BCryptPasswordEncoder().encode(aluno.getSenha()));
			usuarioRepository.save(aluno);
			
			ModelAndView modelAndView = new ModelAndView("errorpage/errorpage");
			modelAndView.addObject("objaluno", new Usuario());
			modelAndView.addObject("listaalunos", usuarioRepository.findAll());
			return modelAndView;
	}
	

	@GetMapping("/cadastro/listaraluno")
	public ModelAndView acessarAluno() {
		ModelAndView modelAndView = new ModelAndView("cadastro/listaraluno");
		modelAndView.addObject("listaalunos", usuarioRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/cadastro/cadastroaluno/{id}")
	public ModelAndView editarAluno(@PathVariable("id") Long id) {
		
		Optional<Usuario> alunos = usuarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		modelAndView.addObject("objaluno", alunos.get());
		modelAndView.addObject("listaalunos", usuarioRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/cadastro/cadastroaluno/deletar/{id}")
	public ModelAndView deletarAluno(@PathVariable("id") Long id) {
		
		Optional<Usuario> alunos = usuarioRepository.findById(id);
		usuarioRepository.delete(alunos.get());
		return acessarAluno();
	}
	
}
