package com.fema.maracai.sgh.dispensacaoMedicamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.dispensacaoMedicamento.dto.DispensacaoMedicamentoDTO;
import com.fema.maracai.sgh.dispensacaoMedicamento.model.DispensacaoMedicamento;
import com.fema.maracai.sgh.dispensacaoMedicamento.repository.DispensacaoMedicamentoRepository;

@Service
public class DispensacaoMedicamentoService {

	@Autowired
	private DispensacaoMedicamentoRepository diMedRepository;
	
	public List<DispensacaoMedicamentoDTO> buscarTodas() {
		List<DispensacaoMedicamento> diMed = this.diMedRepository.findAll();
		List<DispensacaoMedicamentoDTO> diMedDTO = DispensacaoMedicamentoDTO.converter(diMed);
		return diMedDTO;
	}
	
	@Transactional
	public DispensacaoMedicamentoDTO deletar(Long id) {
		return null;
	}

}
