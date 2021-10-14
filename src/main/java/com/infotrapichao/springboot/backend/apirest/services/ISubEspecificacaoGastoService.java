package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.SubEspecificacaoGasto;

public interface ISubEspecificacaoGastoService {

	public List<SubEspecificacaoGasto> findAll();
	public Page<SubEspecificacaoGasto> findAll(Pageable pageable);
	public SubEspecificacaoGasto findById(Long id);
	public SubEspecificacaoGasto save(SubEspecificacaoGasto subEspecificacaoGasto);
	public void delete(Long id);
	
}

