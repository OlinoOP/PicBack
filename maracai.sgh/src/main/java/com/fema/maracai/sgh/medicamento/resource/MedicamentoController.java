package com.fema.maracai.sgh.medicamento.resource;

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

import com.fema.maracai.sgh.medicamento.form.MedicamentoForm;
import com.fema.maracai.sgh.medicamento.service.MedicamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosMedicamentos() {
		return new ResponseEntity<>(this.medicamentoService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirMedicamento(@Valid @RequestBody MedicamentoForm form) {
		this.medicamentoService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarMedicamento(@PathVariable Long id,@Valid @RequestBody MedicamentoForm form) {
		this.medicamentoService.atualizar(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarMedicamento(@PathVariable Long id) {
		this.medicamentoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
