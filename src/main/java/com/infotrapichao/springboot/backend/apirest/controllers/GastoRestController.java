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

import com.infotrapichao.springboot.backend.apirest.entity.Gasto;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;
import com.infotrapichao.springboot.backend.apirest.services.IGastoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class GastoRestController {
	
	@Autowired
	private IGastoService gastoService;

/****************GET ALL****************/
	
	@GetMapping("/gastos")
	public List<Gasto> index(){
		return gastoService.findAll();
	}
	
	/****************GET ALL PAGEABLE****************/
	
	@GetMapping("/gastos/page/{page}")
	public Page<Gasto> index(@PathVariable Integer page){
		return gastoService.findAll(PageRequest.of(page, 4));
	}
	
/****************GET ALL Viagens***************/
	
	@GetMapping("/gastos/viagens")
	public List<Viajem> listarViagens(){
		return gastoService.findAllViagens();
	}
	

	
	/****************GET SHOW****************/
	
	@GetMapping("/gastos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Gasto gasto = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			 
			gasto  = gastoService.findById(id);	
			
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao realizar consulta no DB");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(gasto  == null) {
			response.put("mensagem", "Gasto: ".concat(id.toString().concat(" não existe!!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Gasto>(gasto , HttpStatus.OK);
	}
	
	/****************POST****************/
	
	@PostMapping("/gastos")
	public ResponseEntity<?> create(@Valid @RequestBody Gasto gasto, BindingResult result) {
		Gasto newGasto= null;
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
			newGasto = gastoService.save(gasto);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao inserir o Gasto na base de dados");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensagem", "Gasto inserida na base de dados com sucesso");
		response.put("gasto", newGasto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	/****************PUT****************/
	
	@PutMapping("gastos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Gasto gasto, BindingResult result, @PathVariable Long id) {
		
		Gasto gastoAtual = gastoService.findById(id);
		Gasto gastoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		

		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+ err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		
		if(gastoAtual == null) {
			response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			gastoAtual.setImagem(gasto.getImagem());
			gastoAtual.setLatitude(gasto.getLatitude());
			gastoAtual.setValor(gasto.getValor());
			gastoAtual.setLongitude(gasto.getLongitude());
			gastoAtual.setViajem(gasto.getViajem());
			
			gastoUpdated =  gastoService.save(gastoAtual);
		
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao atualizar o Gasto na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensagem", "Atualizado na base com sucesso!!!");
		response.put("gasto", gastoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		}
	
	/****************DELETE****************/
		
	@DeleteMapping("/gastos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			gastoService.delete(id);
		}catch (DataAccessException e) {
			
			response.put("mensagem", "Erro, não foi possivel deletar o gasto na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			
		}
			
		response.put("mensagem", "Gasto deletado da base");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}}
