package com.infotrapichao.springboot.backend.apirest.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;

public interface ISetorDao extends JpaRepository<Setor, Long>{

	//@Query("from Setor")
	//public List<Setor> findAllSetores();
}
