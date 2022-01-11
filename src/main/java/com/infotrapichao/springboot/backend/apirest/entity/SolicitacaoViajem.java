package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import utils.StatusSolicitacao;

	@Entity
	@Table(name = "solicitacoes_viagens")
	public class SolicitacaoViajem implements Serializable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name="status")
		@NotNull(message = "Status é Obrigatório!!!")
		private StatusSolicitacao status;
					
			
		
		public Long getId() {
			return id;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public StatusSolicitacao getStatus() {
			return status;
		}




		public void setStatus(StatusSolicitacao status) {
			this.status = status;
		}




		private static final long serialVersionUID = 1L;

		
	}
	
	
	
	
