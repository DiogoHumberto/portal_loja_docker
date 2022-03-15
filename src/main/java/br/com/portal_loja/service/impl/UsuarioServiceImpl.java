package br.com.portal_loja.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portal_loja.controller.dto.UsuarioRegistrationDto;
import br.com.portal_loja.entity.Role;
import br.com.portal_loja.entity.Usuario;
import br.com.portal_loja.repository.UsuarioRepository;
import br.com.portal_loja.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	 public Usuario save(UsuarioRegistrationDto registration){
	        Usuario user = new Usuario();
	        user.setDataCastro(Calendar.getInstance().getTime());
	        user.setDataAlterado(Calendar.getInstance().getTime());
	        user.setNome(registration.getNome());
	        user.setSobrenome(registration.getSobrenome());
	        user.setEmail(registration.getEmail());
	        user.setPassword(passwordEncoder.encode(registration.getPassword()));
	        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
	        return userRepository.save(user);
	    }

	@Override
	@Transactional
	public Optional<Usuario> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Usuario> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("nome de usuário ou senha inválidos.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
