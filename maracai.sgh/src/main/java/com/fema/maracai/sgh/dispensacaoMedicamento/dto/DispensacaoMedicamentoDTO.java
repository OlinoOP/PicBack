package com.fema.maracai.sgh.dispensacaoMedicamento.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.dispensacaoMedicamento.model.DispensacaoMedicamento;
import com.fema.maracai.sgh.estoqueMedicamento.model.EstoqueMedicamento;
import com.fema.maracai.sgh.usuario.model.Usuario;

import lombok.Getter;

@Getter
public class DispensacaoMedicamentoDTO {
	private Long id;
	private String dataDispensacao;
	private Long quantidadeDispensada;
	private EstoqueMedicamento estoqueMedicamento;
	private Usuario usuario;
	
	public DispensacaoMedicamentoDTO(DispensacaoMedicamento diMed) {
		id = diMed.getId();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataDispensacao = diMed.getDataDispensacao().format(formatter);
		quantidadeDispensada = diMed.getQuantidadeDispensada();
		estoqueMedicamento = diMed.getEstoqueMedicamento();
		usuario = diMed.getUsuario();
	}
	
	public static List<DispensacaoMedicamentoDTO> converter(List<DispensacaoMedicamento> diMedList) {
		diMedList = diMedList.stream().sorted((d1, d2) -> d1.getDataDispensacao().compareTo(d2.getDataDispensacao())).collect(Collectors.toList());
		return diMedList.stream().map(DispensacaoMedicamentoDTO::new).collect(Collectors.toList());
		
	}
}
