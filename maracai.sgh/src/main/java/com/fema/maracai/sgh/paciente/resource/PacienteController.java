package com.fema.maracai.sgh.paciente.resource;

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

import com.fema.maracai.sgh.paciente.form.PacienteForm;
import com.fema.maracai.sgh.paciente.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosPacientes(){
		return new ResponseEntity<>(this.pacienteService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirPaciente(@Valid @RequestBody PacienteForm form){
		this.pacienteService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteForm form){
		this.pacienteService.atualizar(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPaciente(@PathVariable Long id){
		this.pacienteService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
