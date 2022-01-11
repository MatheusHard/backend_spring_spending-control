package com.infotrapichao.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;
import com.infotrapichao.springboot.backend.apirest.entity.SolicitacaoViajem;

public interface ISolicitacaoViajemDao  extends JpaRepository<SolicitacaoViajem, Long>{

}
