package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
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
@Table(name="funcionarios")
@Getter
@Setter
public class Funcionario implements Serializable{
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		

		@NotNull(message = "Setor não pode ser vazio!!!")
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "setor_id")
		@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
		private Setor setor; 
		
		@NotEmpty(message = "Nome é Obrigatório!!!")
		@Size(min = 3, max = 100, message = "Tamanho deve ser entre 3 e 100 caracteres!!!")
		@Column(nullable = false)
		private String nome;
		

		@NotEmpty(message = "Cpf é Obrigatório!!!")
		@Size(max = 11, message = "Tamanho deve ser entre 11 caracteres!!!")
		@Column(nullable = false)
		private String cpf;
		
		@NotEmpty(message = "email é Obrigatório!!!")
		@Column(nullable = false, unique=true)
		private String email;
		
		@Column(nullable = true)
		private String telefone;
		
			
		public Setor getSetor() {
			return setor;
		}

		public void setSetor(Setor setor) {
			this.setor = setor;
		}




		@Column(name="create_at")
		@Temporal(TemporalType.DATE)
		private Date createAt;
				
		@PrePersist
		public void prePersist() {
			this.createAt = new Date();
		}
						
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
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

























