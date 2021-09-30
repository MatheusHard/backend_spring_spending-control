package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IEspecificacaoGastoDao;
import com.infotrapichao.springboot.backend.apirest.entity.EspecificacaoGasto;

@Service
public class EspecificacaoGastoService implements IEspecificacaoGastoService {

	
	@Autowired
	private IEspecificacaoGastoDao especificacaoGastoDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<EspecificacaoGasto> findAll() {
		return (List<EspecificacaoGasto>) especificacaoGastoDao.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Page<EspecificacaoGasto> findAll(Pageable pageable) {
		return especificacaoGastoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public EspecificacaoGasto findById(Long id) {
		return especificacaoGastoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public EspecificacaoGasto save(EspecificacaoGasto especificacaoGasto) {
		return especificacaoGastoDao.save(especificacaoGasto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		especificacaoGastoDao.deleteById(id);		
	}

}
