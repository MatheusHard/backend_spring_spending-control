package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;

public interface ISetorService {

	public List<Setor> findAll();
	public Page<Setor> findAll(Pageable pageable);
	public Setor findById(Long id);
	public Setor save(Setor setor);
	public void delete(Long id);
	
}
