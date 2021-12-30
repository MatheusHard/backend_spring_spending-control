package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Setor;


public interface IFuncionarioDao  extends JpaRepository<Funcionario, Long>{
	@Query("from Setor")
	public List<Setor> findAllSetores();
	
	@Query("SELECT f FROM Funcionario f WHERE f.cpf = ?1 AND f.password = ?2")
	public Funcionario findByCpfAndPassword(String cpf, String password);
	
	
}
