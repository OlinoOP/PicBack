package com.fema.maracai.sgh.medico.resource;

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

import com.fema.maracai.sgh.medico.form.MedicoForm;
import com.fema.maracai.sgh.medico.form.MedicoFormSemSenha;
import com.fema.maracai.sgh.medico.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodosMedicos(){
		return new ResponseEntity<>(this.medicoService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> inserirMedico(@Valid @RequestBody MedicoForm form){
		this.medicoService.inserir(form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarMedico(@PathVariable Long id, @Valid @RequestBody MedicoFormSemSenha form){
		this.medicoService.atualizar(id, form);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarMedico(@PathVariable Long id){
		this.medicoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}