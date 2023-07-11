package com.fema.maracai.sgh.exameSolicitado.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.exame.dto.ExameDTO;
import com.fema.maracai.sgh.exameSolicitado.model.ExameSolicitado;
import com.fema.maracai.sgh.medico.dto.MedicoDTO;
import com.fema.maracai.sgh.paciente.dto.PacienteDTO;

import lombok.Getter;

@Getter
public class ExameSolicitadoDTO {
    private Long id;
    private String descricao;
    private String dataExame;
    private ExameDTO exame;
    private PacienteDTO paciente;
    private MedicoDTO medico;

    public ExameSolicitadoDTO(ExameSolicitado exameSolicitado) {
        this.id = exameSolicitado.getId();
        this.descricao = exameSolicitado.getDescricao();
        this.dataExame = exameSolicitado.getDataExame().toString();
        this.exame = new ExameDTO(exameSolicitado.getExame());
        this.paciente = new PacienteDTO(exameSolicitado.getPaciente());
        this.medico = new MedicoDTO(exameSolicitado.getMedico());
    }

    public static List<ExameSolicitadoDTO> converter(List<ExameSolicitado> exames) {
        return exames.stream().map(ExameSolicitadoDTO::new).collect(Collectors.toList());
    }
}