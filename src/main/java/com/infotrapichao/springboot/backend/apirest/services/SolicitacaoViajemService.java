package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.ICidadeDao;
import com.infotrapichao.springboot.backend.apirest.dao.ISolicitacaoViajemDao;
import com.infotrapichao.springboot.backend.apirest.entity.Cidade;
import com.infotrapichao.springboot.backend.apirest.entity.SolicitacaoViajem;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;

@Service
public class SolicitacaoViajemService implements ISolicitacaoViajemService {

	@Autowired
	private ISolicitacaoViajemDao solicitacaoViajemDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<SolicitacaoViajem> findAll() {
		return (List<SolicitacaoViajem>) solicitacaoViajemDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<SolicitacaoViajem> findAll(Pageable pageable) {
		return solicitacaoViajemDao.findAll(pageable) ;
	}

	@Override
	@Transactional(readOnly = true)
	public SolicitacaoViajem findById(Long id) {
		return solicitacaoViajemDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public SolicitacaoViajem save(SolicitacaoViajem solicitacaoViajem) {
		return solicitacaoViajemDao.save(solicitacaoViajem);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		solicitacaoViajemDao.deleteById(id);
		
	}

}
