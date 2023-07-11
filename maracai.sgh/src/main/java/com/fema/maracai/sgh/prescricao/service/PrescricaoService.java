package com.fema.maracai.sgh.prescricao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.medicamento.model.Medicamento;
import com.fema.maracai.sgh.medicamento.service.MedicamentoService;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.medico.service.MedicoService;
import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.paciente.service.PacienteService;
import com.fema.maracai.sgh.prescricao.dto.PrescricaoDTO;
import com.fema.maracai.sgh.prescricao.form.PrescricaoForm;
import com.fema.maracai.sgh.prescricao.model.Prescricao;
import com.fema.maracai.sgh.prescricao.repository.PrescricaoRepository;

@Service
public class PrescricaoService {

	@Autowired
	private PrescricaoRepository prescricaoRepository;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private MedicamentoService medicamentoService;
	
	public Prescricao buscarPrescricao(Long id) {
		Optional<Prescricao> prescricao = this.prescricaoRepository.findById(id);
		if(prescricao.isPresent()) {
			return prescricao.get();
		}
		throw new ObjectNotFoundException("Prescrição não encontrada");
	}
	
	public List<PrescricaoDTO> buscarTodas() {
		List<Prescricao> prescricao = this.prescricaoRepository.findAll();
		List<PrescricaoDTO> prescricaoDTO = PrescricaoDTO.converter(prescricao);
		return prescricaoDTO;
	}
	
	@Transactional
	public PrescricaoDTO criarPrescricao(PrescricaoForm form) {
		Medicamento medicamento = this.medicamentoService.buscarMedicamento(form.getMedicamento().getId());
		Medico medico = this.medicoService.buscarMedico(form.getMedico().getId());
		Paciente paciente = this.pacienteService.buscarPaciente(form.getPaciente().getId());
		Prescricao prescricao = this.criaPrescricaoComForm(form);
		prescricao.setMedicamento(medicamento);
		prescricao.setPaciente(paciente);
		prescricao.setMedico(medico);
		prescricao.setDataPrescricao(LocalDate.now());
		this.prescricaoRepository.save(prescricao);
		return new PrescricaoDTO(prescricao);
	}
	
	private Prescricao criaPrescricaoComForm(PrescricaoForm form) {
		return Prescricao.builder()
				.dosagem(form.getDosagem())
				.duracao(form.getDuracao())
				.frequencia(form.getFrequencia())
				.build();
	}

	@Transactional
	public PrescricaoDTO atualizarPrescricao(Long id, PrescricaoForm form) {
		Prescricao prescricao = this.buscarPrescricao(id);
		Medicamento medicamento = this.medicamentoService.buscarMedicamento(form.getMedicamento().getId());
		Medico medico = this.medicoService.buscarMedico(form.getMedico().getId());
		Paciente paciente = this.pacienteService.buscarPaciente(form.getPaciente().getId());
		prescricao = this.criaPrescricaoComForm(form);
		prescricao.setId(id);
		prescricao.setMedicamento(medicamento);
		prescricao.setPaciente(paciente);
		prescricao.setMedico(medico);
		this.prescricaoRepository.save(prescricao);
		return new PrescricaoDTO(prescricao);
	}
	


	@Transactional
	public PrescricaoDTO deletarPrescricao(Long id) {
		Prescricao prescricao = this.buscarPrescricao(id);
		this.prescricaoRepository.delete(prescricao);
		return new PrescricaoDTO(prescricao);
	}

}
