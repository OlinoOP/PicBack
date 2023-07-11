package com.fema.maracai.sgh.dispensacaoMedicamento.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fema.maracai.sgh.dispensacaoMedicamento.service.DispensacaoMedicamentoService;

@RestController
@RequestMapping("/dispensacao/medicamento")
public class DispensacaoMedicamentoController {

	@Autowired
	private DispensacaoMedicamentoService dispensacaoMedicamentoService;
	
	@GetMapping
	public ResponseEntity<?> buscarTodasDispensacao() {
		return new ResponseEntity<>(this.dispensacaoMedicamentoService.buscarTodas(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarDispensacao(@PathVariable Long id) {
		this.dispensacaoMedicamentoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
