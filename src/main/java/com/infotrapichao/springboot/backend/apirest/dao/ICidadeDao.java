package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infotrapichao.springboot.backend.apirest.entity.Cidade;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;


public interface ICidadeDao extends JpaRepository<Cidade, Long>{
	
	@Query("from Uf")
	public List<Uf> findAllUfs();

}
