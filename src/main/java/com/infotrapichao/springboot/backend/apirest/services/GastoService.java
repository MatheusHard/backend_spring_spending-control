package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IGastoDao;
import com.infotrapichao.springboot.backend.apirest.entity.Gasto;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

@Service
public class GastoService implements IGastoService{


	@Autowired
	private IGastoDao gastoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Gasto> findAll() {
		return  (List<Gasto>) gastoDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Gasto> findAll(Pageable pageable) {
		return gastoDao.findAll(pageable) ;
	}

	@Override
	@Transactional(readOnly = true)
	public Gasto findById(Long id) {
		return gastoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Gasto save(Gasto gasto) {
		return gastoDao.save(gasto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		gastoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Viajem> findAllViagens() {
		return gastoDao.findAllViagens();
	}

	
}

