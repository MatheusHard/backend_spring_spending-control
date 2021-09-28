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

import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
import com.infotrapichao.springboot.backend.apirest.entity.Viajem;
import com.infotrapichao.springboot.backend.apirest.services.IViajemService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ViajemRestController {

	@Autowired
	private IViajemService viajemService;
	
	/****************GET ALL****************/
	
	@GetMapping("/viagens")
	public List<Viajem> index(){
		return viajemService.findAll();
	}
	
	/****************GET ALL PAGEABLE****************/
	
	@GetMapping("/viagens/page/{page}")
	public Page<Viajem> index(@PathVariable Integer page){
		return viajemService.findAll(PageRequest.of(page, 4));
	}
	
/****************GET ALL UFS****************/
	
	@GetMapping("/viagens/funcionarios")
	public List<Funcionario> listarFuncionarios(){
		return viajemService.findAllFuncionarios();
	}
	

	
	/****************GET SHOW****************/
	
	@GetMapping("/viagens/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Viajem viajem = null;
		Map<String, Object> response = new HashMap<>();
		
		try{
			 
			viajem  = viajemService.findById(id);	
			
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao realizar consulta no DB");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(viajem  == null) {
			response.put("mensagem", "A viajem: ".concat(id.toString().concat(" não existe!!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Viajem>(viajem , HttpStatus.OK);
	}
	
	/****************POST****************/
	
	@PostMapping("/viagens")
	public ResponseEntity<?> create(@Valid @RequestBody Viajem viajem, BindingResult result) {
		Viajem newViajem = null;
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
			newViajem = viajemService.save(viajem);
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro ao inserir a Viajem na base de dados");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		response.put("mensagem", "Viajem inserida na base de dados com sucesso");
		response.put("viajem", newViajem);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	/****************PUT****************/
	
	@PutMapping("viagens/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Viajem viajem , BindingResult result, @PathVariable Long id) {
		
		Viajem viajemAtual = viajemService.findById(id);
		Viajem viajemUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		

		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors().
						stream().
						map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
						collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

		}
		
		
		if(viajemAtual == null) {
			response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
			viajemAtual.setDataInicial(viajem.getDataInicial());
			viajemAtual.setDataFinal(viajem.getDataFinal());
			viajemAtual.setSaldo(viajem.getSaldo());
			viajemAtual.setGastoTotal(viajem.getGastoTotal());
			viajemAtual.setFuncionario(viajem.getFuncionario());
			viajemAtual.setCidade(viajem.getCidade());
			
			viajemUpdated =  viajemService.save(viajemAtual);
		
		}catch (DataAccessException e) {
			response.put("mensagem", "Erro al atualizar a Viajem na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensagem", "Atualizado na base com sucesso!!!");
		response.put("viajem", viajemUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		}
	
	/****************DELETE****************/
		
	@DeleteMapping("/viagens/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			viajemService.delete(id);
		}catch (DataAccessException e) {
			
			response.put("mensagem", "Erro, não foi possivel deletar a viajem na base");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			
		}
			
		response.put("mensagem", "Viajem deletada da base");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
	}}
