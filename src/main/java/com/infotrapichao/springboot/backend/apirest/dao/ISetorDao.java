package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;

public interface ISetorDao extends JpaRepository<Setor, Long>{

	//@Query("from Setor")
	//public List<Setor> findAllSetores();
}
