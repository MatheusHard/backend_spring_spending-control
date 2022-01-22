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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import utils.StatusSolicitacao;

@Entity
@Table(name = "viagens")
@Getter
@Setter
public class Viajem implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Data Inicial não pode estar vazia!!!")
	@Column(name="data_inicial")
	@Temporal(TemporalType.DATE)
	private Date dataInicial;
    
    public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	@NotNull(message = "Data final não pode estar vazia!!!")
	@Column(name="data_final")
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	
    //@Range(min = 0, max = 10)
	@Column(name = "saldo", nullable = false)
	private double saldo;
	
	@Column(name="status")
	@NotNull(message = "Status é Obrigatório!!!")
	private StatusSolicitacao status;
    
    public StatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacao status) {
		this.status = status;
	}

	//@Range(min = 0, max = 10)
  	@Column(name="gasto_total", nullable = false)
  	private double gastoTotal;
        
	//@JoinColumn(name = "funcionario_id")
  	@NotNull(message = "Funcionario não pode ser vazio!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"viagens", "hibernateLazyInitializer", "handler"})
	private Funcionario funcionario; 
    
    @NotNull(message = "Cidade não pode ser vazia!!!")
   	@ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "cidade_id")
   	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
   	private Cidade cidade; 
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "viajem"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viajem", cascade = CascadeType.ALL)
	private List<Gasto> gastos;
    
    @JsonIgnoreProperties({"sub_especificacoes_gastos", "especificacaoGasto", "viajem_especificacao", "hibernateLazyInitializer", "handler"})
  	@OneToMany(fetch = FetchType.LAZY, mappedBy = "viajem_especificacao", cascade = CascadeType.ALL)
  	private List<EspecificacaoGasto> especificacoes_gastos;
      
   
    public List<EspecificacaoGasto> getEspecificacoes_gastos() {
		return especificacoes_gastos;
	}

	public void setEspecificacoes_gastos(List<EspecificacaoGasto> especificacoes_gastos) {
		this.especificacoes_gastos = especificacoes_gastos;
	}

	public Viajem() {
    	this.gastos = new ArrayList<Gasto>();
    	this.especificacoes_gastos = new ArrayList<EspecificacaoGasto>();
    }
     
    @Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
			
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	    
    public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	 public double getSaldo() {
	    	
	    	return saldo;
		}

	

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public double getGastoTotal() {
			
		double sum = 0;
        
    	for (Gasto gasto : this.getGastos()) {
    	    sum += gasto.getValor(); 
    		}
    	gastoTotal = sum;
    	    	
		return gastoTotal;
	}


	public void setGastoTotal(double gastoTotal) {
		this.gastoTotal = gastoTotal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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


















