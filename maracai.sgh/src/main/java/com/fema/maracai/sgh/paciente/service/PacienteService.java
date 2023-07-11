package com.fema.maracai.sgh.paciente.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.paciente.dto.PacienteDTO;
import com.fema.maracai.sgh.paciente.form.PacienteForm;
import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.paciente.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente buscarPaciente(Long id) {
		Optional<Paciente> paciente = this.pacienteRepository.findById(id);
		if(paciente.isPresent()) {
			return paciente.get();
		}
		throw new ObjectNotFoundException("Paciente não encontrado!");
	}
	
	public List<PacienteDTO> buscarTodos() {
		List<Paciente> pacientes = pacienteRepository.findAll();
		List<PacienteDTO> pacientesDTO = PacienteDTO.converter(pacientes);
		return pacientesDTO;
	}
	
	@Transactional
	public PacienteDTO inserir(PacienteForm form) {
		Paciente paciente = criaPacienteComForm(form);
		this.pacienteRepository.save(paciente);
		return new PacienteDTO(paciente);
		
	}

	private Paciente criaPacienteComForm(PacienteForm form) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Paciente paciente = Paciente.builder().nome(form.getNome())
				.cidade(form.getCidade())
				.dataNascimento(LocalDate.parse(form.getDataNascimento(), dateFormatter))
				.cpf(form.getCpf())
				.endereco(form.getEndereco())
				.estado(form.getEstado())
				.sexo(form.getSexo())
				.telefone(form.getTelefone())
				.build();
		return paciente;
	}
	
	@Transactional
	public PacienteDTO atualizar(Long id, PacienteForm form) {
		Paciente paciente = buscarPaciente(id);
		if(paciente.getId().equals(id)) {
			paciente = criaPacienteComForm(form);
			paciente.setId(id);
			this.pacienteRepository.save(paciente);
		}
		return new PacienteDTO(paciente);	
	}

	@Transactional
	public PacienteDTO deletar(Long id) {
		Paciente paciente = buscarPaciente(id);
		if(paciente.getId().equals(id)) {
			this.pacienteRepository.delete(paciente);
		}
		return new PacienteDTO(paciente);
	}
}
