package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.infotrapichao.springboot.backend.apirest.entity.Gasto;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

public interface IGastoService {

	public List<Gasto> findAll();
	public Page<Gasto> findAll(Pageable pageable);
	public List<Viajem> findAllViagens();
	public Gasto findById(Long id);
	public Gasto save(Gasto gasto);
	public void delete(Long id);

}
