package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IUsuarioDao;
import com.infotrapichao.springboot.backend.apirest.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService{

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			logger.error("Erro no login: esse Usuario não existe '"+username+"' no Sistema");
			throw new UsernameNotFoundException("Erro no login: esse Usuario não existe '"+username+"' no Sistema");
		} 
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> {
					return new SimpleGrantedAuthority(role.getNome());
					})
				.peek(authority -> logger.info("Role "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

}




























