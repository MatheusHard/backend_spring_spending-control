package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IViajemDao;
import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

@Service
public class ViajemService implements IViajemService{

	@Autowired
	private IViajemDao viajemDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Viajem> findAll() {
		return  (List<Viajem>) viajemDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Viajem> findAll(Pageable pageable) {
		return viajemDao.findAll(pageable) ;
	}

	@Override
	@Transactional(readOnly = true)
	public Viajem findById(Long id) {
		return viajemDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Viajem save(Viajem viajem) {
		return viajemDao.save(viajem);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		viajemDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> findAllFuncionarios() {
		return viajemDao.findAllFuncionarios();
	}

	
}
