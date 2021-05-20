package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;


public interface IFuncionarioService {
	
	//public List<Funcionario> findFuncionariosBySetor();
	public List<Funcionario> findAll();
	public Funcionario findById(Long id);
	public Funcionario save(Funcionario funcionario);
	public void delete(Long id);
	
}
