package com.fema.maracai.sgh.estoqueMedicamento.resource;

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

import com.fema.maracai.sgh.estoqueMedicamento.form.EstoqueMedicamentoForm;
import com.fema.maracai.sgh.estoqueMedicamento.service.EstoqueMedicamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estoque/medicamento")
public class EstoqueMedicamentoController {

	@Autowired
	private EstoqueMedicamentoService etqMedicamentoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodoEstoque() {
		return new ResponseEntity<>(this.etqMedicamentoService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirEstoque(@Valid @RequestBody EstoqueMedicamentoForm form) {
		this.etqMedicamentoService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{idEstoque}/{idUsuario}")
	public ResponseEntity<?> atualizarEstoque(@PathVariable Long idEstoque, @PathVariable Long idUsuario, @Valid @RequestBody EstoqueMedicamentoForm form) {
		this.etqMedicamentoService.atualizar(idEstoque, idUsuario, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEstoque(@PathVariable Long id) {
		this.etqMedicamentoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
