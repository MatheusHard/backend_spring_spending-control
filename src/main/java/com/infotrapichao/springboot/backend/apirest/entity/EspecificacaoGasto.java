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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @Column(name="valor_especificacao")
	private double valor_especificacao;
  	
    
	public double getValor_especificacao() {
		return valor_especificacao;
	}


	public void setValor_especificacao(double valor_especificacao) {
		this.valor_especificacao = valor_especificacao;
	}


	@ManyToOne(fetch = FetchType.LAZY)
   	@JsonIgnoreProperties({"hibernateLazyInitializer", "especificacoes_gastos", "handler"})
   	private Viajem viajem_especificacao; 
    
    public Viajem getViajem_especificacao() {
		return viajem_especificacao;
	}


	public void setViajem_especificacao(Viajem viajem_especificacao) {
		this.viajem_especificacao = viajem_especificacao;
	}


	@JsonIgnoreProperties({"especificacaoGasto", "especificacoes_gastos", "hibernateLazyInitializer", "handler"})
  	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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