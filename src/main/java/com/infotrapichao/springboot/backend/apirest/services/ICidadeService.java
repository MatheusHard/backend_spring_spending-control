package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.Cidade;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;

public interface ICidadeService {

	public List<Cidade> findAll();
	public Page<Cidade> findAll(Pageable pageable);
	public List<Uf> findAllUfs();
	public Cidade findById(Long id);
	public Cidade save(Cidade cidade);
	public void delete(Long id);
	
}

