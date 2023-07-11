package com.fema.maracai.sgh.exame.resource;

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

import com.fema.maracai.sgh.exame.form.ExameForm;
import com.fema.maracai.sgh.exame.service.ExameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/exame")
public class ExameController {

	@Autowired
	private ExameService exameService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosExames() {
		return new ResponseEntity<>(this.exameService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirExame(@Valid @RequestBody ExameForm form) {
		this.exameService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarExame(@PathVariable Long id, @Valid @RequestBody ExameForm form) {
		this.exameService.atualizar(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarExame(@PathVariable Long id) {
		this.exameService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
