package com.infotrapichao.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Cidade;


public interface ICidadeDao extends CrudRepository<Cidade, Long>{

}
