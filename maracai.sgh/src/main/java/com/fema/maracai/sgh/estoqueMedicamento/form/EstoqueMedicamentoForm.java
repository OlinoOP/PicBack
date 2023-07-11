package com.fema.maracai.sgh.estoqueMedicamento.form;

import com.fema.maracai.sgh.medicamento.model.Medicamento;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EstoqueMedicamentoForm {
	@NotNull
	private Medicamento medicamento;
	@NotNull
	private Long quantidade;
	@NotNull
	private String dataValidade;
	@NotNull
	private String lote;
}
