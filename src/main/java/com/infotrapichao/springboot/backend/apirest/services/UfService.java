package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IUfDao;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;

@Service
public class UfService implements IUfService {

	@Autowired
	private IUfDao ufDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Uf> findAll() {
		return (List<Uf>) ufDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Uf findById(Long id) {
		return ufDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Uf save(Uf uf) {
		return ufDao.save(uf);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ufDao.deleteById(id);
		
	}


}
