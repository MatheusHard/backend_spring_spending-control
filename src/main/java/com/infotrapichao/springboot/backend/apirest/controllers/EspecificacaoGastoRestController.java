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

import com.infotrapichao.springboot.backend.apirest.entity.EspecificacaoGasto;
import com.infotrapichao.springboot.backend.apirest.services.IEspecificacaoGastoService;


	@CrossOrigin(origins = {"http://localhost:4200"})
	@RestController
	@RequestMapping("/api")
	public class EspecificacaoGastoRestController {

		@Autowired
		private IEspecificacaoGastoService especificacaoGastoService;
		
		/****************GET ALL****************/
		
		@GetMapping("/especificacao_gastos")
		public List<EspecificacaoGasto> index(){
			return especificacaoGastoService.findAll();
		}
		
	/****************GET ALL PAGEABLE****************/
		
		@GetMapping("/especificacao_gastos/page/{page}")
		public Page<EspecificacaoGasto> index(@PathVariable Integer page){
			return especificacaoGastoService.findAll(PageRequest.of(page, 4));
		}
		/****************GET SHOW****************/
		
		//@Secured({"ROLE_ADMIN", "ROLE_USER"})
		@GetMapping("/especificacao_gastos/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> show(@PathVariable Long id) {
			
			EspecificacaoGasto especificacaoGasto = null;
			Map<String, Object> response = new HashMap<>();
			
			try{
				especificacaoGasto = especificacaoGastoService.findById(id);	
				
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao realizar consulta no DB");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(especificacaoGasto == null) {
				response.put("mensagem", "A EspecificacaoGasto: ".concat(id.toString().concat(" não existe!!!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<EspecificacaoGasto>(especificacaoGasto, HttpStatus.OK);
		}
		
		/****************POST****************/
		
		//@Secured({"ROLE_ADMIN"})
		@PostMapping("/especificacao_gastos")
		public ResponseEntity<?> create(@Valid @RequestBody EspecificacaoGasto especificacaoGasto, BindingResult result) {
			EspecificacaoGasto newEspecificacaoGasto = null;

			System.out.println("Especificacao:");
			System.out.println(especificacaoGasto);
			
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
				newEspecificacaoGasto = especificacaoGastoService.save(especificacaoGasto);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao inserir a EspecificacaoGasto na base de dados");
				response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			response.put("mensagem", "EspecificacaoGasto inserida na base de dados com sucesso");
			response.put("especificacaoGasto", newEspecificacaoGasto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
		}
		
		/****************PUT****************/
		
		//@Secured({"ROLE_ADMIN"})
		@PutMapping("especificacao_gastos/{id}")
		public ResponseEntity<?> update(@Valid @RequestBody EspecificacaoGasto especificacaoGasto, BindingResult result, @PathVariable Long id) {
			
			EspecificacaoGasto especificacaoGastoAtual = especificacaoGastoService.findById(id);
			EspecificacaoGasto especificacaoGastoUpdated = null;
			
			Map<String, Object> response = new HashMap<>();
			

			if(result.hasErrors()) {
				
				List<String> errors = result.getFieldErrors().
							stream().
							map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
							collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

			}
			
			
			if(especificacaoGastoAtual == null) {
				response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try {
			
				especificacaoGastoAtual.setDescricao_especificacao_gasto(especificacaoGasto.getDescricao_especificacao_gasto());
				
				especificacaoGastoUpdated =  especificacaoGastoService.save(especificacaoGastoAtual);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro al atualizar o setor na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensagem", "Atualizado na base com sucesso!!!");
			response.put("especificacaoGasto", especificacaoGastoUpdated);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
			
			}
		
		/****************DELETE****************/
		
		//@Secured({"ROLE_ADMIN"})
		@DeleteMapping("/especificacao_gastos/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<>();

			try {
				especificacaoGastoService.delete(id);
			}catch (DataAccessException e) {
				
				response.put("mensagem", "Erro, não foi possivel deletar a EspecificacaoGasto na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
				
			}
				
			response.put("mensagem", "EspecificacaoGasto deletada da base");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
		}}


