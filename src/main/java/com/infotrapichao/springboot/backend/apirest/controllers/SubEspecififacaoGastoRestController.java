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

import com.infotrapichao.springboot.backend.apirest.entity.SubEspecificacaoGasto;
import com.infotrapichao.springboot.backend.apirest.services.ISubEspecificacaoGastoService;



	@CrossOrigin(origins = {"http://localhost:4200"})
	@RestController
	@RequestMapping("/api")
	public class SubEspecififacaoGastoRestController {

		@Autowired
		private ISubEspecificacaoGastoService subEspecificacaoGastoService;
		
		/****************GET ALL****************/
		
		@GetMapping("/sub_especificacao_gastos")
		public List<SubEspecificacaoGasto> index(){
			return subEspecificacaoGastoService.findAll();
		}
		
	/****************GET ALL PAGEABLE****************/
		
		@GetMapping("/sub_especificacao_gastos/page/{page}")
		public Page<SubEspecificacaoGasto> index(@PathVariable Integer page){
			return subEspecificacaoGastoService.findAll(PageRequest.of(page, 4));
		}
		/****************GET SHOW****************/
		
		//@Secured({"ROLE_ADMIN", "ROLE_USER"})
		@GetMapping("/sub_especificacao_gastos/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> show(@PathVariable Long id) {
			
			SubEspecificacaoGasto subEspecificacaoGasto = null;
			Map<String, Object> response = new HashMap<>();
			
			try{
				subEspecificacaoGasto= subEspecificacaoGastoService.findById(id);	
				
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao realizar consulta no DB");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(subEspecificacaoGasto == null) {
				response.put("mensagem", "A SubEspecificacaoGasto: ".concat(id.toString().concat(" não existe!!!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<SubEspecificacaoGasto>(subEspecificacaoGasto, HttpStatus.OK);
		}
		
		/****************POST****************/
		
		//@Secured({"ROLE_ADMIN"})
		@PostMapping("/sub_especificacao_gastos")
		public ResponseEntity<?> create(@Valid @RequestBody SubEspecificacaoGasto subEspecificacaoGasto, BindingResult result) {
			SubEspecificacaoGasto newSubEspecificacaoGasto = null;

			//System.out.println("Especificacao:");
			//System.out.println(especificacaoGasto);
			
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
				newSubEspecificacaoGasto = subEspecificacaoGastoService.save(subEspecificacaoGasto);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao inserir a SubEspecificacaoGasto na base de dados");
				response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			response.put("mensagem", "SubEspecificacaoGasto inserida na base de dados com sucesso");
			response.put("subEspecififacaoGasto", newSubEspecificacaoGasto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
		}
		
		/****************PUT****************/
		
		//@Secured({"ROLE_ADMIN"})
		@PutMapping("sub_especificacao_gastos/{id}")
		public ResponseEntity<?> update(@Valid @RequestBody SubEspecificacaoGasto subEspecificacaoGasto, BindingResult result, @PathVariable Long id) {
			
			SubEspecificacaoGasto subEspecificacaoGastoAtual = subEspecificacaoGastoService.findById(id);
			SubEspecificacaoGasto subEspecificacaoGastoUpdated = null;
			
			Map<String, Object> response = new HashMap<>();
			

			if(result.hasErrors()) {
				
				List<String> errors = result.getFieldErrors().
							stream().
							map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
							collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

			}
			
			
			if(subEspecificacaoGastoAtual == null) {
				response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try {
			
				subEspecificacaoGastoAtual.setDescricao_sub_especificacao_gasto(subEspecificacaoGasto.getDescricao_sub_especificacao_gasto());
				
				subEspecificacaoGastoUpdated =  subEspecificacaoGastoService.save(subEspecificacaoGastoAtual);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao atualizar a SubEspecificacaoGasto na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensagem", "Atualizado na base com sucesso!!!");
			response.put("subEspecificacaoGasto", subEspecificacaoGastoUpdated);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
			
			}
		
		/****************DELETE****************/
		
		//@Secured({"ROLE_ADMIN"})
		@DeleteMapping("/sub_especificacao_gastos/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<>();

			try {
				subEspecificacaoGastoService.delete(id);
			}catch (DataAccessException e) {
				
				response.put("mensagem", "Erro, não foi possivel deletar a SubEspecificacaoGasto da base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
				
			}
				
			response.put("mensagem", "SubEspecificacaoGasto deletada da base");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
		}}
