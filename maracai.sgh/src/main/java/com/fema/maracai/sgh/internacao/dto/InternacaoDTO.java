package com.fema.maracai.sgh.internacao.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.internacao.model.Internacao;
import com.fema.maracai.sgh.leito.dto.LeitoDTO;
import com.fema.maracai.sgh.medico.dto.MedicoDTO;
import com.fema.maracai.sgh.paciente.dto.PacienteDTO;

import lombok.Getter;

@Getter
public class InternacaoDTO {

    private Long id;
    private String dataEntrada;
    private String dataSaida;
    private String diagnostico;
    private LeitoDTO leito;
    private MedicoDTO medico;
    private PacienteDTO paciente;

    public InternacaoDTO(Internacao internacao) {
        this.id = internacao.getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataEntrada = internacao.getDataEntrada().format(formatter);
        this.dataSaida = internacao.getDataSaida() != null ? internacao.getDataSaida().format(formatter) : null;
        this.diagnostico = internacao.getDiagnostico();
        this.leito = new LeitoDTO(internacao.getLeito());
        this.medico = new MedicoDTO(internacao.getMedico());
        this.paciente = new PacienteDTO(internacao.getPaciente());
    }

    public static List<InternacaoDTO> converter(List<Internacao> internacoes) {
        return internacoes.stream().map(InternacaoDTO::new).collect(Collectors.toList());
    }
}

