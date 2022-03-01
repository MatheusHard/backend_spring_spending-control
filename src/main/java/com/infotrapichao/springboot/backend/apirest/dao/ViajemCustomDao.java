package com.infotrapichao.springboot.backend.apirest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.infotrapichao.springboot.backend.apirest.entity.Viajem;

import utils.StatusSolicitacao;

@Repository
public class ViajemCustomDao {

	 private final EntityManager em;

	    public ViajemCustomDao(EntityManager em) {
	        this.em = em;
	    }

	    public List<Viajem> find(Long id, StatusSolicitacao status) {

	        String query = "select V from Viajem as V ";
	        String condicao = "where";

	        if(id != null) {
	            query += condicao + " V.id = :id";
	            condicao = " and ";
	        }

	        if(status != null) {
	            query += condicao + " V.status = :status";
	            condicao = " and ";
	        }

	      

	        var q = em.createQuery(query, Viajem.class);

	        if(id != null) {
	            q.setParameter("id", id);
	        }

	        if(status != null) {
	            q.setParameter("status", status);
	        }

	       

	        return q.getResultList();
	    }

	}

