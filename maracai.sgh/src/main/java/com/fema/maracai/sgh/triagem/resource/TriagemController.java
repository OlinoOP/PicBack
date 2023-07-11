package com.fema.maracai.sgh.triagem.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fema.maracai.sgh.triagem.dto.TriagemDTO;
import com.fema.maracai.sgh.triagem.form.TriagemForm;
import com.fema.maracai.sgh.triagem.service.TriagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/triagem")
public class TriagemController {

	@Autowired
	private TriagemService triagemService;
	
	@GetMapping
	public ResponseEntity<List<TriagemDTO>> buscarTodasTriagens(){
		List<TriagemDTO> triagens = triagemService.buscarTodasTriagens();
		return ResponseEntity.ok(triagens);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserirTriagem(@Valid @RequestBody TriagemForm form){
		triagemService.inserirTriagem(form);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarTriagem(@PathVariable Long id, @Valid @RequestBody TriagemForm form){
		triagemService.atualizarTriagem(id, form);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarTriagem(@PathVariable Long id){
		triagemService.deletarTriagem(id);
		return ResponseEntity.ok().build();
	}
}

