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
@Table(name="cidades")
@Getter
@Setter
public class Cidade  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Descricao Cidade é Obrigatório!!!")
	@Size(min = 2, max = 25, message = "Tamanho deve ser entre 2 e 25 caracteres!!!")
	@Column(nullable = false)
	private String descricao_cidade;
	
	@NotNull(message = "Uf não pode ser vazio!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uf_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Uf uf; 
	
	//@NotNull(message = "não pode estar vazio-  ")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
			
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao_cidade() {
		return descricao_cidade;
	}

	public void setDescricao_cidade(String descricao_cidade) {
		this.descricao_cidade = descricao_cidade;
	}

	/*public Long getFk_uf() {
		return fk_uf;
	}

	public void setFk_uf(Long fk_uf) {
		this.fk_uf = fk_uf;
	}*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
