package com.fema.maracai.sgh.medicamento.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.medicamento.model.Medicamento;

import lombok.Getter;

@Getter
public class MedicamentoDTO {
	private Long id;
	private String nomeComercial;
	private String nomeGenerico;
	private String fabricante;
	private String concentracao;
	private String unidadeMedida;
	
	public MedicamentoDTO (Medicamento medicamento) {
		id = medicamento.getId();
		nomeComercial = medicamento.getNomeComercial();
		nomeGenerico = medicamento.getNomeGenerico();
		fabricante = medicamento.getFabricante();
		concentracao = medicamento.getConcentracao();
		unidadeMedida = medicamento.getUnidadeMedida();
	}
	
	public static List<MedicamentoDTO> converter(List<Medicamento> medicamentos){
		medicamentos = medicamentos.stream().sorted((m1, m2) -> m1.getNomeComercial().compareTo(m2.getNomeComercial())).collect(Collectors.toList());
		return medicamentos.stream().map(MedicamentoDTO::new).collect(Collectors.toList());
	}
}
