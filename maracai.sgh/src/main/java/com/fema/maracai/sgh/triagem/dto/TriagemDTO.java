package com.fema.maracai.sgh.triagem.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.paciente.dto.PacienteDTO;
import com.fema.maracai.sgh.triagem.model.Triagem;
import com.fema.maracai.sgh.usuario.dto.UsuarioDTO;

import lombok.Getter;

@Getter
public class TriagemDTO {
    private Long id;
    private String dataTriagem;
    private BigDecimal peso;
    private BigDecimal altura;
    private String pressao;
    private BigDecimal temperatura;
    private String sintomas;
    private String observacoes;
    private Long statusTriagem;
    private PacienteDTO paciente;
    private UsuarioDTO usuario;

    public TriagemDTO(Triagem triagem) {
        id = triagem.getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataTriagem = triagem.getDataTriagem().format(formatter);
        peso = triagem.getPeso();
        altura = triagem.getAltura();
        pressao = triagem.getPressao();
        temperatura = triagem.getTemperatura();
        sintomas = triagem.getSintomas();
        observacoes = triagem.getObservacoes();
        statusTriagem = triagem.getStatusTriagem();
        paciente = new PacienteDTO(triagem.getPaciente());
        usuario = new UsuarioDTO(triagem.getUsuario());
    }

    public static List<TriagemDTO> converter(List<Triagem> triagens) {
        return triagens.stream().map(TriagemDTO::new).collect(Collectors.toList());
    }
}
