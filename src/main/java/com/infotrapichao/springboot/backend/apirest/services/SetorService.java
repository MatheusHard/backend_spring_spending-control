package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.ISetorDao;
import com.infotrapichao.springboot.backend.apirest.entity.Setor;



@Service
public class SetorService implements ISetorService{

	@Autowired
	private ISetorDao setorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Setor> findAll() {
		return (List<Setor>) setorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Setor findById(Long id) {
		return setorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Setor save(Setor cliente) {
		return setorDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		setorDao.deleteById(id);
		
	}

	
	
	
}

















