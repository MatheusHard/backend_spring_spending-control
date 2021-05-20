package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import com.infotrapichao.springboot.backend.apirest.entity.Uf;

public interface IUfService {

	public List<Uf> findAll();
	public Uf findById(Long id);
	public Uf save(Uf uf);
	public void delete(Long id);
}
