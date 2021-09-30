package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.EspecificacaoGasto;

public interface IEspecificacaoGastoService {

	public List<EspecificacaoGasto> findAll();
	public Page<EspecificacaoGasto> findAll(Pageable pageable);
	public EspecificacaoGasto findById(Long id);
	public EspecificacaoGasto save(EspecificacaoGasto especificacaoGasto);
	public void delete(Long id);
	
}
