package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "especificacao_gastos")
@Getter
@Setter
public class EspecificacaoGasto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Especificação do Gasto é Obrigatória!!!")
	@Size(min = 2, max = 50, message = "Tamanho deve ser entre 2 e 50 caracteres!!!")
	@Column(nullable = false)
	private String descricao_especificacao_gasto;
    
    @Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
    
    @JsonIgnoreProperties({"especificacaoGasto", "hibernateLazyInitializer", "handler"})
  	@OneToMany(fetch = FetchType.LAZY, mappedBy = "especificacaoGasto", cascade = CascadeType.ALL)
  	private List<SubEspecificacaoGasto> sub_especificacoes_gastos;
      
    /*Construtor inicializado com SubEspecificacao*/
      public EspecificacaoGasto() {
      	this.sub_especificacoes_gastos = new ArrayList<SubEspecificacaoGasto>();
      }
     
	
	public List<SubEspecificacaoGasto> getSub_especificacoes_gastos() {
		return sub_especificacoes_gastos;
	}



	public void setSub_especificacoes_gastos(List<SubEspecificacaoGasto> sub_especificacoes_gastos) {
		this.sub_especificacoes_gastos = sub_especificacoes_gastos;
	}



	public Long getId() {
		return id;
	}
	
	public String getDescricao_especificacao_gasto() {
		return descricao_especificacao_gasto;
	}

	public void setDescricao_especificacao_gasto(String descricao_especificacao_gasto) {
		this.descricao_especificacao_gasto = descricao_especificacao_gasto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
			
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	  
  	
	private static final long serialVersionUID = 1L;
	    
}