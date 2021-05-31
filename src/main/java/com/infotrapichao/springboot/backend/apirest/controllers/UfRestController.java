package com.infotrapichao.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.infotrapichao.springboot.backend.apirest.entity.Uf;
import com.infotrapichao.springboot.backend.apirest.services.IUfService;

		@CrossOrigin(origins = {"http://localhost:4200"})
		@RestController
		@RequestMapping("/api")
		public class UfRestController {

		@Autowired
		private IUfService ufService;
		
		/****************GET ALL****************/
		
		@GetMapping("/ufs")
		public List<Uf> index(){
			return ufService.findAll();
		}
		
		/****************GET SHOW****************/
		
		@GetMapping("/ufs/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> show(@PathVariable Long id) {
			
			Uf uf = null;
			Map<String, Object> response = new HashMap<>();
			
			try{
				uf = ufService.findById(id);	
				
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao realizar consulta no DB");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(uf == null) {
				response.put("mensagem", "O Estado: ".concat(id.toString().concat(" não existe!!!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<Uf>(uf, HttpStatus.OK);
		}
		
		/****************POST****************/
		
		@PostMapping("/ufs")
		public ResponseEntity<?> create(@Valid @RequestBody Uf uf, BindingResult result) {
			Uf newUf = null;
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
				newUf = ufService.save(uf);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao inserir o Estado na base de dados");
				response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			response.put("mensagem", "Uf inserido na base de dados com sucesso");
			response.put("uf", newUf);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
		}
		
		/****************PUT****************/
		
		@PutMapping("ufs/{id}")
		public ResponseEntity<?> update(@Valid @RequestBody Uf uf, BindingResult result, @PathVariable Long id) {
			
			Uf ufAtual = ufService.findById(id);
			Uf ufUpdated = null;
			
			Map<String, Object> response = new HashMap<>();
		
			if(result.hasErrors()) {
				
				List<String> errors = result.getFieldErrors().
							stream().
							map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
							collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

			}
			
			
			if(ufAtual == null) {
				response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try {
			
				ufAtual.setDescricao_uf(uf.getDescricao_uf());
				
				ufUpdated =  ufService.save(ufAtual);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro al atualizar o estado na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensagem", "Atualizado na base com sucesso!!!");
			response.put("uf", ufUpdated);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
						
			}
		
		/****************DELETE****************/
			
		@DeleteMapping("/ufs/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<>();

			try {
				ufService.delete(id);
			}catch (DataAccessException e) {
				
				response.put("mensagem", "Erro ,  não foi possivel deletar o estado na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
				
			}
				
			response.put("mensagem", "Uf deletado da base");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
		}}



