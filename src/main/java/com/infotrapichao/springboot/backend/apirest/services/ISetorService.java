package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;

public interface ISetorService {

	public List<Setor> findAll();
	public Setor findById(Long id);
	public Setor save(Setor cliente);
	public void delete(Long id);
	
}
