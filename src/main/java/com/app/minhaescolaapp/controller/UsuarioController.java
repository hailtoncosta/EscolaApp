package com.app.minhaescolaapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Usuario;
import com.app.minhaescolaapp.repository.UsuarioRepository;
import com.app.minhaescolaapp.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario/cadastrarusuarios")
	public ModelAndView cadastrarUsuarios() {
		ModelAndView modelAndView = new ModelAndView("usuario/cadastrarusuarios");
		modelAndView.addObject("objusuarios", new Usuario());
		return modelAndView;
	}
	
	@PostMapping("/salvarusuario")
	public ModelAndView salvarUsuario(Usuario usuario) {
		usuarioService.salvarUsuario(usuario);
		return new ModelAndView("redirect:/usuario/listarusuarios");
	}
	
	@GetMapping("/usuario/listarusuarios")
	public ModelAndView listarUsuario() {
		ModelAndView modelAndView = new ModelAndView("/usuario/listarusuarios");
		modelAndView.addObject("listarusuarios", usuarioRepository.findAll(PageRequest.of(0, 5, Sort.by("login"))));
		return modelAndView;
	}
	
	@GetMapping("/usuario/deletarusuario/{id}")
	public ModelAndView deletarUsuario(@PathVariable("id") Long id) {
		usuarioService.deletarUsuario(id);
		return new ModelAndView("redirect:/usuario/listarusuarios");
	}
	
	@GetMapping("/usuario/editarusuario/{id}")
	public ModelAndView editarUsuario(@PathVariable("id") Long id) {
		Optional<Usuario> usuarios = usuarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("usuario/cadastrarusuarios");
		modelAndView.addObject("objusuarios", usuarios.get());
		return modelAndView;
	}
	
	@GetMapping("/usuariospage")
	public ModelAndView carregaUsuariosPorPaginacao(@PageableDefault(size = 5) Pageable pageable, ModelAndView modelAndView) {
		
		Page<Usuario> pageUsuario = usuarioRepository.findAll(pageable);
		modelAndView.addObject("listarusuarios", pageUsuario);
		modelAndView.addObject("objusuarios", new Usuario());
		modelAndView.setViewName("/usuario/listarusuarios");
		
		return modelAndView;
	}
	
	@PostMapping("/pesquisarusuario")
	public ModelAndView pesquisarPaciente(@RequestParam("nomepesquisa") String nomepesquisa, 
			@PageableDefault(size=5, sort= {"login"}) Pageable pageable) {
		
		Page<Usuario> usuarios = null;
		
		usuarios = usuarioRepository.findUsuariosByNamePage(nomepesquisa, pageable);
		
		ModelAndView modelAndView = new ModelAndView("usuario/listarusuarios");
		modelAndView.addObject("listarusuarios", usuarios);
		modelAndView.addObject("objusuarios", new Usuario());
		modelAndView.addObject("nomepesquisa", nomepesquisa);
		
		return modelAndView;
	}
}
