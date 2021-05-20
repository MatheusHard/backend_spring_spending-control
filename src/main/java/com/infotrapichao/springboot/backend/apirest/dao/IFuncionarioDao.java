package com.infotrapichao.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;


public interface IFuncionarioDao  extends CrudRepository<Funcionario, Long>{

}
