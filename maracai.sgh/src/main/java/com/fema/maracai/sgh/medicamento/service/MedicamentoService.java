package com.fema.maracai.sgh.medicamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.medicamento.dto.MedicamentoDTO;
import com.fema.maracai.sgh.medicamento.form.MedicamentoForm;
import com.fema.maracai.sgh.medicamento.model.Medicamento;
import com.fema.maracai.sgh.medicamento.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	public Medicamento buscarMedicamento(Long id) {
		Optional<Medicamento> medicamento = this.medicamentoRepository.findById(id);
		if(medicamento.isPresent()) {
			return medicamento.get();
		}
		throw new ObjectNotFoundException("Medicamento não encontrado");
	}
	
	public List<MedicamentoDTO> buscarTodos() {
		List<Medicamento> medicamentos = medicamentoRepository.findAll();
		List<MedicamentoDTO> medicamentosDTO = MedicamentoDTO.converter(medicamentos);
		return medicamentosDTO;
	}
	
	@Transactional
	public MedicamentoDTO inserir(MedicamentoForm form) {
		Medicamento medicamento = criaMedicamentoComForm(form);
		this.medicamentoRepository.save(medicamento);
		return new MedicamentoDTO(medicamento);
	}

	private Medicamento criaMedicamentoComForm(MedicamentoForm form) {
		return Medicamento.builder()
				.concentracao(form.getConcentracao())
				.fabricante(form.getFabricante())
				.nomeComercial(form.getNomeComercial())
				.nomeGenerico(form.getNomeGenerico())
				.unidadeMedida(form.getUnidadeMedida())
				.build();
	}
	
	@Transactional
	public MedicamentoDTO atualizar(Long id, MedicamentoForm form) {
		Medicamento medicamento = this.buscarMedicamento(id);
		medicamento = this.criaMedicamentoComForm(form);
		medicamento.setId(id);
		this.medicamentoRepository.save(medicamento);
		return new MedicamentoDTO(medicamento);
	}

	@Transactional
	public MedicamentoDTO deletar(Long id) {
		Medicamento medicamento = this.buscarMedicamento(id);
		this.medicamentoRepository.delete(medicamento);
		return new MedicamentoDTO(medicamento);
	}

}
