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
	@Table(name="ufs")
	@Getter
	@Setter
	public class Uf implements Serializable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message = "Nome é Obrigatório!!!")
		@Size(min = 2, max = 25, message = "Tamanho deve ser entre 2 e 25 caracteres!!!")
		@Column(nullable = false)
		private String descricao_uf;
		

		@NotEmpty(message = "Nome é Obrigatório!!!")
		@Size(min = 2, max = 2, message = "Tamanho deve ser de 2 caracteres!!!")
		@Column(nullable = false)
		private String sigla_uf;
		
		/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "uf", cascade = CascadeType.ALL)
		private List<Cidade> cidades;
		
		public List<Cidade> getCidades() {
			return cidades;
		}

		public void setCidades(List<Cidade> cidades) {
			this.cidades = cidades;
		}*/

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescricao_uf() {
			return descricao_uf;
		}

		public void setDescricao_uf(String descricao_uf) {
			this.descricao_uf = descricao_uf;
		}

		public String getSigla_uf() {
			return sigla_uf;
		}

		public void setSigla_uf(String sigla_uf) {
			this.sigla_uf = sigla_uf;
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
