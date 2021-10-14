package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.ISubEspecificacaoGastoDao;
import com.infotrapichao.springboot.backend.apirest.entity.SubEspecificacaoGasto;

@Service
public class SubEspecificacaoGastoService  implements ISubEspecificacaoGastoService {

	
	@Autowired
	private ISubEspecificacaoGastoDao subEspecificacaoGastoDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<SubEspecificacaoGasto> findAll() {
		return (List<SubEspecificacaoGasto>) subEspecificacaoGastoDao.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public Page<SubEspecificacaoGasto> findAll(Pageable pageable) {
		return subEspecificacaoGastoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public SubEspecificacaoGasto findById(Long id) {
		return subEspecificacaoGastoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public SubEspecificacaoGasto save(SubEspecificacaoGasto subEspecificacaoGasto) {
		return subEspecificacaoGastoDao.save(subEspecificacaoGasto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		subEspecificacaoGastoDao.deleteById(id);		
	}

}
