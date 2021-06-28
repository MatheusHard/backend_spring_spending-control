package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

public interface IViajemDao extends JpaRepository<Viajem, Long>{
	
	@Query("from Uf")
	public List<Funcionario> findAllFuncionarios();

}

