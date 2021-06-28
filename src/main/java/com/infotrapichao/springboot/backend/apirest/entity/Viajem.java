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

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

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
    
    @NotNull(message = "Data final não pode estar vazia!!!")
	@Column(name="data_final")
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	
    @Range(min = 0, max = 10)
	@Column(name = "saldo", nullable = false)
	private double saldo;
    
    @Range(min = 0, max = 10)
  	@Column(name="gasto_total", nullable = false)
  	private double gastoTotal;
    
    @NotNull(message = "Funcionario não pode ser vazio!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Funcionario funcionario; 
    
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


















