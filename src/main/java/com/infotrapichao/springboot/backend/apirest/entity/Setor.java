package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="setores")
@Getter
@Setter
public class Setor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome é Obrigatório!!!")
	@Size(min = 3, max = 100, message = "Tamanho deve ser entre 3 e 100 caracteres!!!")
	@Column(nullable = false)
	private String descricao_setor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao_setor() {
		return descricao_setor;
	}

	public void setDescricao_setor(String descricao_setor) {
		this.descricao_setor = descricao_setor;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
