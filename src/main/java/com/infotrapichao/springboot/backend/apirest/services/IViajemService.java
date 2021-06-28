package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

public interface IViajemService {

	public List<Viajem> findAll();
	public Page<Viajem> findAll(Pageable pageable);
	public List<Funcionario> findAllFuncionarios();
	public Viajem findById(Long id);
	public Viajem save(Viajem viajem);
	public void delete(Long id);
	
}
