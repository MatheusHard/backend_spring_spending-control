package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Setor;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;


public interface IFuncionarioDao  extends CrudRepository<Funcionario, Long>{
	@Query("from Setor")
	public List<Setor> findAllSetores();
	
}
