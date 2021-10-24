package com.infotrapichao.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "gastos")
@Getter
@Setter
public class Gasto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Fornecedor Obrigatório!!!")
	@Size(min = 3, max = 100, message = "Tamanho deve ser entre 3 e 100 caracteres!!!")
	@Column(nullable = false)
	private String fornecedor;
	
	@NotNull(message = "Data do Gasto não pode estar vazia!!!")
	@Column(name="data_gasto")
	@Temporal(TemporalType.DATE)
	private Date data_gasto;
	
	
	@NotEmpty(message = "Cpf é Obrigatório!!!")
	@Size(max = 11, min= 11, message = "Tamanho deve ser entre 11 caracteres!!!")
	@Column(nullable = false)
	private String cpf_devedor;
	
	
	@Column(name="valor")
	@NotNull(message = "Valor Obrigatório!!!")
	private double valor;
  	
  	@NotEmpty(message = "Latitude é Obrigatória!!!")
	@Column(nullable = false)
	private String latitude;
    
  	@NotEmpty(message = "Longitude é Obrigatória!!!")
	@Column(nullable = false)
	private String longitude;
    
  	
  	//@JoinColumn(name = "viajem_id")
  	@NotNull(message = "Viajem não pode ser vazia!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"gastos", "hibernateLazyInitializer", "handler"})
	private Viajem viajem; 
  	
  	
	
	
	@Column(name="subespecificacao_id", nullable = false)
	private Long subespecificacao_id;
	
  	
	//, cascade = CascadeType.ALL@NotNull(message = "SubEspecificação não pode ser vazia!!!")
  	/*@JsonIgnoreProperties({"gasto", "hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	private SubEspecificacaoGasto subEspecificacaoGasto;
	
	 
    */
  	/*@NotNull(message = "Subespecificacao do Gasto não pode ser vazia!!!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subespecificacao_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private SubEspecificacaoGasto subEspecificacaoGasto; 
  	
  	public SubEspecificacaoGasto getSubEspecificacaoGasto() {
		return subEspecificacaoGasto;
	}

	public void setSubEspecificacaoGasto(SubEspecificacaoGasto subEspecificacaoGasto) {
		this.subEspecificacaoGasto = subEspecificacaoGasto;
	}*/

	public Long getSubespecificacao_id() {
		return subespecificacao_id;
	}

	public void setSubespecificacao_id(Long subespecificacao_id) {
		this.subespecificacao_id = subespecificacao_id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getData_gasto() {
		return data_gasto;
	}

	public void setData_gasto(Date data_gasto) {
		this.data_gasto = data_gasto;
	}
	
  	public String getCpf_devedor() {
		return cpf_devedor;
	}

	public void setCpf_devedor(String cpf_devedor) {
		this.cpf_devedor = cpf_devedor;
	}
  	/*
    public SubEspecificacaoGasto getSubEspecificacaoGasto() {
		return subEspecificacaoGasto;
	}

	public void setSubEspecificacaoGasto(SubEspecificacaoGasto subEspecificacaoGasto) {
		this.subEspecificacaoGasto = subEspecificacaoGasto;
	}*/

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
			
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
  	@NotNull(message = "Viajem não pode ser vazia!!!")
  	@Column(name="imagem", nullable = false)
  	private String imagem;
    
  	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Viajem getViajem() {
		return viajem;
	}

	public void setViajem(Viajem viajem) {
		this.viajem = viajem;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Double getSomaValores() {
		return this.getValor();
	}

	
	private static final long serialVersionUID = 1L;
	    
}