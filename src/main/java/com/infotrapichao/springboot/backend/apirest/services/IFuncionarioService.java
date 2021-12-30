package com.infotrapichao.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Setor;


public interface IFuncionarioService {
	
	public List<Funcionario> findAll();
	public Page<Funcionario> findAll(Pageable pageable);
	public List<Setor> findAllSetores();
	public Funcionario findById(Long id);
	public Funcionario save(Funcionario funcionario);
	public void delete(Long id);
	public Funcionario findByCpfAndPassword(String cpf, String password);
	
	
}
