package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.ICidadeDao;
import com.infotrapichao.springboot.backend.apirest.entity.Cidade;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;

@Service
public class CidadeService implements ICidadeService{


	@Autowired
	private ICidadeDao cidadeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cidade> findAll() {
		return (List<Cidade>) cidadeDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cidade> findAll(Pageable pageable) {
		return cidadeDao.findAll(pageable) ;
	}

	@Override
	@Transactional(readOnly = true)
	public Cidade findById(Long id) {
		return cidadeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cidade save(Cidade cidade) {
		return cidadeDao.save(cidade);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cidadeDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uf> findAllUfs() {
		return cidadeDao.findAllUfs();
	}

	

	
	
	
}
