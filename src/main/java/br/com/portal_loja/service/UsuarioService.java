package br.com.portal_loja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.portal_loja.controller.dto.UsuarioRegistrationDto;
import br.com.portal_loja.entity.Usuario;

public interface UsuarioService extends UserDetailsService {
	
	Optional<Usuario> findById(Long id);
	
	List<Usuario> findAll();
	
	public Usuario save(UsuarioRegistrationDto registration);

}
