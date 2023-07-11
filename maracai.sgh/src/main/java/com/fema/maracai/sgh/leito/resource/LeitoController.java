package com.fema.maracai.sgh.leito.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fema.maracai.sgh.leito.form.LeitoForm;
import com.fema.maracai.sgh.leito.service.LeitoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leito")
public class LeitoController {

	@Autowired
	private LeitoService leitoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosLeitos(){
		return new ResponseEntity<>(this.leitoService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirLeito(@Valid @RequestBody LeitoForm form){
		this.leitoService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarLeito(@PathVariable Long id, @Valid @RequestBody LeitoForm form){
		this.leitoService.atualizar(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarLeito(@PathVariable Long id){
		this.leitoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
