package com.fema.maracai.sgh.prescricao.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.medicamento.dto.MedicamentoDTO;
import com.fema.maracai.sgh.medico.dto.MedicoDTO;
import com.fema.maracai.sgh.paciente.dto.PacienteDTO;
import com.fema.maracai.sgh.prescricao.model.Prescricao;

import lombok.Getter;

@Getter
public class PrescricaoDTO {
	private Long id;
	private String dosagem;
	private String frequencia;
	private String duracao;
	private String dataPrescricao;
	private MedicamentoDTO medicamento;
	private MedicoDTO medico;
	private PacienteDTO paciente;
	
	public PrescricaoDTO(Prescricao prescricao) {
		id = prescricao.getId();
		dosagem = prescricao.getDosagem();
		frequencia = prescricao.getFrequencia();
		duracao = prescricao.getDuracao();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataPrescricao = prescricao.getDataPrescricao().format(formatter);
		medicamento = new MedicamentoDTO(prescricao.getMedicamento());
		medico = new MedicoDTO(prescricao.getMedico());
		paciente = new PacienteDTO(prescricao.getPaciente());
	}
	
	public static List<PrescricaoDTO> converter(List<Prescricao> prescricoes){
		prescricoes = prescricoes.stream().sorted((p1, p2) -> p2.getDataPrescricao().compareTo(p1.getDataPrescricao())).collect(Collectors.toList());
		return prescricoes.stream().map(PrescricaoDTO::new).collect(Collectors.toList());
	}
}
