package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infotrapichao.springboot.backend.apirest.entity.Gasto;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

public interface IGastoDao extends JpaRepository<Gasto, Long>{
	
	@Query("from Funcionario")
	public List<Viajem> findAllViagens();
	
}
