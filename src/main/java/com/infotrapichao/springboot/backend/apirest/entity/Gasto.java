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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	//tipo_gasto
	
	
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

	
	private static final long serialVersionUID = 1L;
	    
}