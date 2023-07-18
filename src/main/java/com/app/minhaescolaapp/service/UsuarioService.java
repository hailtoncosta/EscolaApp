package com.app.minhaescolaapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.app.minhaescolaapp.model.Usuario;
import com.app.minhaescolaapp.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.encryptPassword();
		return usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public void editarUsuario(@PathVariable("id") Long id, ModelAndView modelAndView) {
		Optional<Usuario> usuarios = usuarioRepository.findById(id);
		modelAndView.addObject("editarusuario", usuarios.get());
	}
}
