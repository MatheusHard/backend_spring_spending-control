package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotrapichao.springboot.backend.apirest.dao.IFuncionarioDao;
import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;

@Service
public class FuncionarioService implements IFuncionarioService{

 	
	@Autowired
	private IFuncionarioDao funcionarioDao;
	
		
	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return (List<Funcionario>) funcionarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario findById(Long id) {
		return funcionarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		return funcionarioDao.save(funcionario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		funcionarioDao.deleteById(id);
		
	}

	
}