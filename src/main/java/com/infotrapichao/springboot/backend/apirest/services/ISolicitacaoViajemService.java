package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infotrapichao.springboot.backend.apirest.entity.SolicitacaoViajem;

public interface ISolicitacaoViajemService {

	public List<SolicitacaoViajem> findAll();
	public Page<SolicitacaoViajem> findAll(Pageable pageable);
	public SolicitacaoViajem findById(Long id);
	public SolicitacaoViajem save(SolicitacaoViajem solicitacaoViajem);
	public void delete(Long id);
}
