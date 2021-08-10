package com.infotrapichao.springboot.backend.apirest.services;

import com.infotrapichao.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
