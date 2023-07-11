package com.fema.maracai.sgh.prescricao.resource;

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

import com.fema.maracai.sgh.prescricao.form.PrescricaoForm;
import com.fema.maracai.sgh.prescricao.service.PrescricaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {

	@Autowired
	private PrescricaoService prescricaoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodasPrescricoes() {
		return new ResponseEntity<>(this.prescricaoService.buscarTodas(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> criarPrescricao(@Valid @RequestBody PrescricaoForm form){
		this.prescricaoService.criarPrescricao(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarPrescricao(@PathVariable Long id, @Valid @RequestBody PrescricaoForm form){
		this.prescricaoService.atualizarPrescricao(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPrescricao(@PathVariable Long id){
		this.prescricaoService.deletarPrescricao(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
