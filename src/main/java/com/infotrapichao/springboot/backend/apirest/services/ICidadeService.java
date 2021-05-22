package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import com.infotrapichao.springboot.backend.apirest.entity.Cidade;

public interface ICidadeService {

	public List<Cidade> findAll();
	public Cidade findById(Long id);
	public Cidade save(Cidade cidade);
	public void delete(Long id);
	
}

