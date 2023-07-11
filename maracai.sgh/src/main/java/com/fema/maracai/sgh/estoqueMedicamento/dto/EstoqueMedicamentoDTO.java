package com.fema.maracai.sgh.estoqueMedicamento.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.estoqueMedicamento.model.EstoqueMedicamento;
import com.fema.maracai.sgh.medicamento.model.Medicamento;

import lombok.Getter;

@Getter
public class EstoqueMedicamentoDTO {
	
	private Long id;
	private Medicamento medicamento;
	private Long quantidade;
	private String dataValidade;
	private String lote;
	
	public EstoqueMedicamentoDTO(EstoqueMedicamento etqMedicamento) {
		id = etqMedicamento.getId();
		medicamento = etqMedicamento.getMedicamento();
		quantidade = etqMedicamento.getQuantidade();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataValidade = etqMedicamento.getDataValidade().format(formatter);
		lote = etqMedicamento.getLote();
	}
	
	public static List<EstoqueMedicamentoDTO> converter(List<EstoqueMedicamento> etqMedicamentos){
		etqMedicamentos = etqMedicamentos.stream().sorted((e1, e2) -> e1.getDataValidade().compareTo(e2.getDataValidade())).collect(Collectors.toList());
		return etqMedicamentos.stream().map(EstoqueMedicamentoDTO::new).collect(Collectors.toList());
	}
}
