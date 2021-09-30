package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


	@Entity
	@Table(name = "sub_especififacao_gastos")
	@Getter
	@Setter
	public class SubEspecificacaoGasto implements Serializable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message = "SubEspecificação do Gasto é Obrigatória!!!")
		@Size(min = 2, max = 50, message = "Tamanho deve ser entre 2 e 50 caracteres!!!")
		@Column(nullable = false)
		private String descricao_sub_especificacao_gasto;
		
		@NotNull(message = "EspecificacaoGasto não pode ser vazia!!!")
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnoreProperties({"sub_especififacacoes_gastos", "hibernateLazyInitializer", "handler"})
		private EspecificacaoGasto especificacaoGasto; 
		
		@Column(name="create_at")
		@Temporal(TemporalType.DATE)
		private Date createAt;
	    
	    
	    public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getDescricao_sub_especificacao_gasto() {
			return descricao_sub_especificacao_gasto;
		}


		public void setDescricao_sub_especificacao_gasto(String descricao_sub_especificacao_gasto) {
			this.descricao_sub_especificacao_gasto = descricao_sub_especificacao_gasto;
		}


		public EspecificacaoGasto getEspecificacaoGasto() {
			return especificacaoGasto;
		}


		public void setEspecificacaoGasto(EspecificacaoGasto especificacaoGasto) {
			this.especificacaoGasto = especificacaoGasto;
		}


		public Date getCreateAt() {
			return createAt;
		}


		public void setCreateAt(Date createAt) {
			this.createAt = createAt;
		}


		
	    
	    
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		}
