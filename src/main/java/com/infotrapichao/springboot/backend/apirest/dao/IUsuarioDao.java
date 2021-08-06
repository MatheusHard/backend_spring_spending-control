package com.infotrapichao.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
}
