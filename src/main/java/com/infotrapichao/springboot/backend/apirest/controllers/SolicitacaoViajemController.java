package com.infotrapichao.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infotrapichao.springboot.backend.apirest.entity.SolicitacaoViajem;
import com.infotrapichao.springboot.backend.apirest.services.ISolicitacaoViajemService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class SolicitacaoViajemController {

	@Autowired
	private ISolicitacaoViajemService solicitacaoViajemService;
	
	/****************GET ALL****************/
	
	@GetMapping("/solicitacoes_viajem")
	public List<SolicitacaoViajem> index(){
		return solicitacaoViajemService.findAll();
	}
	
/****************GET ALL PAGEABLE****************/
	
	@GetMapping("/solicitacoes_viajem/page/{page}")
	public Page<SolicitacaoViajem> index(@PathVariable Integer page){
		return solicitacaoViajemService.findAll(PageRequest.of(page, 4));
	}
	/****************GET SHOW****************/
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/solicitacoes_viajem/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		SolicitacaoViajem solicitacaoViajem = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			solicitacaoViajem = solicitacaoViajemService.findById(id);	
			
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao realizar consulta no DB");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(solicitacaoViajem == null) {
			response.put("mensagem", "A SolicitacaoViajem: ".concat(id.toString().concat(" não existe!!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SolicitacaoViajem>(solicitacaoViajem, HttpStatus.OK);
	}
	
	/****************POST****************/
	
	//@Secured({"ROLE_ADMIN"})
	@PostMapping("/solicitacoes_viajem")
	public ResponseEntity<?> create(@Valid @RequestBody SolicitacaoViajem solicitacaoViajem, BindingResult result) {
		SolicitacaoViajem newSolicitacaoViajem = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		try{
			newSolicitacaoViajem = solicitacaoViajemService.save(solicitacaoViajem);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao inserir a SolicitacaoViajem na base de dados");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensagem", "Setor inserido na base de dados com sucesso");
		response.put("solicitacaoViajem", newSolicitacaoViajem);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	/****************PUT****************/
	
	//@Secured({"ROLE_ADMIN"})
	@PutMapping("solicitacoes_viajem/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody SolicitacaoViajem solicitacaoViajem, BindingResult result, @PathVariable Long id) {
		
		SolicitacaoViajem solicitacaoViajemAtual = solicitacaoViajemService.findById(id);
		SolicitacaoViajem solicitacaoViajemUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		

		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		
		if(solicitacaoViajemAtual == null) {
			response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			//TO DO
			solicitacaoViajemAtual.setStatus(solicitacaoViajem.getStatus());
			
			solicitacaoViajemUpdated =  solicitacaoViajemService.save(solicitacaoViajemAtual);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro al atualizar o setor na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensagem", "Atualizado na base com sucesso!!!");
		response.put("solicitacaoViajem", solicitacaoViajemUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		}
	
	/****************DELETE****************/
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/solicitacoes_viajem/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			solicitacaoViajemService.delete(id);
		}catch (DataAccessException e) {
			
			response.put("mensagem", "Erro, não foi possivel deletar o setor na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			
		}
			
		response.put("mensagem", "SolicitacaoViajem deletada da base");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}}


