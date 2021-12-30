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

	import com.infotrapichao.springboot.backend.apirest.entity.Funcionario;
	import com.infotrapichao.springboot.backend.apirest.entity.Setor;
	import com.infotrapichao.springboot.backend.apirest.services.IFuncionarioService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FuncionarioRestController {

		@Autowired
		private IFuncionarioService funcionarioService;
		
		/****************GET ALL****************/
		
		@GetMapping("/funcionarios")
		public List<Funcionario> index(){
			return funcionarioService.findAll();
		}
		

		/****************GET ALL PAGEABLE****************/
		
		@GetMapping("/funcionarios/page/{page}")
		public Page<Funcionario> index(@PathVariable Integer page){
			return funcionarioService.findAll(PageRequest.of(page, 4));
		}
		
		/****************GET ALL SETORES****************/
		
		@GetMapping("/funcionarios/setores")
		public List<Setor> listarSetores(){
			return funcionarioService.findAllSetores();
		}
		
		/****************GET SHOW****************/
		
		//@Secured({"ROLE_ADMIN", "ROLE_USER"})
		@GetMapping("/funcionarios/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> show(@PathVariable Long id) {
			
			Funcionario funcionario = null;
			Map<String, Object> response = new HashMap<>();
			
			try{
				funcionario = funcionarioService.findById(id);	
				
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao realizar consulta no DB");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(funcionario == null) {
				response.put("mensagem", "O Funcionario: ".concat(id.toString().concat(" não existe!!!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		}
		
		
	/****************GET SHOW****************/
		
		@GetMapping("/funcionarios/find_by_login/{cpf}/{password}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<?> show_login(@PathVariable String cpf, @PathVariable String password) {
			
			Funcionario funcionario = null;
			Map<String, Object> response = new HashMap<>();
			
			try{
				funcionario = funcionarioService.findByCpfAndPassword(cpf, password);	
				
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao realizar consulta no DB");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if(funcionario == null) {
				response.put("mensagem", "O Funcionario: ".concat(cpf.toString().concat(" não existe!!!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		}
		
		/****************POST****************/
		
		@Secured({"ROLE_ADMIN"})
		@PostMapping("/funcionarios")
		public ResponseEntity<?> create(@Valid @RequestBody Funcionario funcionario, BindingResult result) {
			Funcionario newFuncionario= null;
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
				newFuncionario = funcionarioService.save(funcionario);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro ao inserir o Funcionario na base de dados");
				response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			response.put("mensagem", "Funcionario inserido na base de dados com sucesso");
			response.put("funcionario", newFuncionario);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
		}
		
		/****************PUT****************/

		@Secured({"ROLE_ADMIN"})
		@PutMapping("funcionarios/{id}")
		public ResponseEntity<?> update(@Valid @RequestBody Funcionario funcionario, BindingResult result, @PathVariable Long id) {
			
			Funcionario funcionarioAtual = funcionarioService.findById(id);
			Funcionario funcionarioUpdated = null;
			
			Map<String, Object> response = new HashMap<>();
			

			if(result.hasErrors()) {
				
				List<String> errors = result.getFieldErrors().
							stream().
							map(err -> "O campo '"+err.getField() + "' "+err.getDefaultMessage()).
							collect(Collectors.toList());
				response.put("errors", errors);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST); 

			}
			
			
			if(funcionarioAtual == null) {
				response.put("mensagem", "Erro: não foi possivel editar o ID: ".concat(id.toString()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try {
			
				funcionarioAtual.setNome(funcionario.getNome());
				funcionarioAtual.setCpf(funcionario.getCpf());
				funcionarioAtual.setEmail(funcionario.getEmail());
				funcionarioAtual.setTelefone(funcionario.getTelefone());
				funcionarioAtual.setSetor(funcionario.getSetor());
				//funcionarioAtual.setViagens(funcionario.getViagens());
				
								
				funcionarioUpdated =  funcionarioService.save(funcionarioAtual);
			}catch (DataAccessException e) {
				response.put("mensagem", "Erro al atualizar o funcionario na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensagem", "Atualizado na base com sucesso!!!");
			response.put("funcionario", funcionarioUpdated);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
			
			}
		
		/****************DELETE****************/
			
		@Secured({"ROLE_ADMIN"})
		@DeleteMapping("/funcionarios/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {
			Map<String, Object> response = new HashMap<>();

			try {
				funcionarioService.delete(id);
			}catch (DataAccessException e) {
				
				response.put("mensagem", "Erro ,  não foi possivel deletar o funcionario na base");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
				
			}
				
			response.put("mensagem", "Funcionario deletado da base");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);		
		}}
