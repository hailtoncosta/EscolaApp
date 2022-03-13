package com.app.minhaescolaapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.minhaescolaapp.model.Aluno;
import com.app.minhaescolaapp.repository.EscolaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EscolaRepository escolaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Aluno aluno = escolaRepository.userFindByLogin(username);
		
		if (aluno == null) {
			throw new UsernameNotFoundException("Aluno não encontrado.");
		}
		return aluno;
	}

}
