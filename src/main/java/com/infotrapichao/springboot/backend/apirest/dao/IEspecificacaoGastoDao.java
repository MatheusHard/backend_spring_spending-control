package com.infotrapichao.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.infotrapichao.springboot.backend.apirest.entity.EspecificacaoGasto;

public interface IEspecificacaoGastoDao extends JpaRepository<EspecificacaoGasto, Long>{

}
