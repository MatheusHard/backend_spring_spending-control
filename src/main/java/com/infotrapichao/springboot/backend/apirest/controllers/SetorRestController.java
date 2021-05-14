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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infotrapichao.springboot.backend.apirest.entity.Setor;
import com.infotrapichao.springboot.backend.apirest.services.ISetorService;

@RestController
@RequestMapping("/api")
public class SetorRestController {

	@Autowired
	private ISetorService setorService;
	
	/****************GET ALL****************/
	
	@GetMapping("/setores")
	public List<Setor> index(){
		return setorService.findAll();
	}
	
	/****************GET SHOW****************/
	
	@GetMapping("/setores/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Setor setor = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			setor = setorService.findById(id);	
			
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao realizar consulta no DB");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(setor == null) {
			response.put("mensagem", "O Cliente: ".concat(id.toString().concat(" não existe!!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Setor>(setor, HttpStatus.OK);
	}
	
	/****************POST****************/
	
	@PostMapping("/setores")
	public ResponseEntity<?> create(@Valid @RequestBody Setor setor, BindingResult result) {
		Setor newSetor = null;
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
			newSetor = setorService.save(setor);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao inserir Setor na base de dados");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensagem", "Setor inserido na base de dados com sucesso");
		response.put("cliente", newSetor);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	/****************PUT****************/
	
	@PutMapping("setores/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Setor setor, BindingResult result, @PathVariable Long id) {
		
		Setor setorAtual = setorService.findById(id);
		Setor setorUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		

		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		
		if(setorAtual == null) {
			response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			setorAtual.setDescricao(setor.getDescricao());
			
			setorUpdated =  setorService.save(setorAtual);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro al atualizar o setor na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensagem", "Atualizado na base com sucesso!!!");
		response.put("cliente", setorUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		}
	
	/****************DELETE****************/
		
	@DeleteMapping("/setores/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			setorService.delete(id);
		}catch (DataAccessException e) {
			
			response.put("mensagem", "Erro ,  não foi possivel deletar o setor na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			
		}
			
		response.put("mensagem", "Setor deletado da base");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}}


































