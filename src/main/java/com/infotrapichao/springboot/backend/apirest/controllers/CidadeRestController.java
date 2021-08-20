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

import com.infotrapichao.springboot.backend.apirest.entity.Cidade;
import com.infotrapichao.springboot.backend.apirest.entity.Uf;
import com.infotrapichao.springboot.backend.apirest.services.ICidadeService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CidadeRestController {

	@Autowired
	private ICidadeService cidadeService;
	
	/****************GET ALL****************/
	
	@GetMapping("/cidades")
	public List<Cidade> index(){
		return cidadeService.findAll();
	}
	
	/****************GET ALL PAGEABLE****************/
	
	@GetMapping("/cidades/page/{page}")
	public Page<Cidade> index(@PathVariable Integer page){
		return cidadeService.findAll(PageRequest.of(page, 4));
	}
	
/****************GET ALL UFS****************/
	
	@GetMapping("/cidades/ufs")
	public List<Uf> listarUfs(){
		return cidadeService.findAllUfs();
	}
	

	
	/****************GET SHOW****************/

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/cidades/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cidade cidade  = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			 
			cidade = cidadeService.findById(id);	
			
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao realizar consulta no DB");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(cidade == null) {
			response.put("mensagem", "A cidade: ".concat(id.toString().concat(" não existe!!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Cidade>(cidade, HttpStatus.OK);
	}
	
	/****************POST****************/
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/cidades")
	public ResponseEntity<?> create(@Valid @RequestBody Cidade cidade, BindingResult result) {
		Cidade newCidade= null;
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
			newCidade = cidadeService.save(cidade);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao inserir a Cidade na base de dados");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensagem", "Cidade inserida na base de dados com sucesso");
		response.put("cidade", newCidade);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	/****************PUT****************/

	@Secured({"ROLE_ADMIN"})
	@PutMapping("cidades/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cidade cidade, BindingResult result, @PathVariable Long id) {
		
		Cidade cidadeAtual = cidadeService.findById(id);
		Cidade cidadeUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		

		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		
		if(cidadeAtual == null) {
			response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			cidadeAtual.setDescricao_cidade(cidade.getDescricao_cidade());
			cidadeAtual.setUf(cidade.getUf());

			
			cidadeUpdated =  cidadeService.save(cidadeAtual);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro al atualizar a Cidade na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensagem", "Atualizado na base com sucesso!!!");
		response.put("cidade", cidadeUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		}
	
	/****************DELETE****************/
		
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/cidades/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			cidadeService.delete(id);
		}catch (DataAccessException e) {
			
			response.put("mensagem", "Erro ,  não foi possivel deletar a cidade na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			
		}
			
		response.put("mensagem", "Cidade deletado da base");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}}

