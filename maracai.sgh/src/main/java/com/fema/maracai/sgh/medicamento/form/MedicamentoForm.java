package com.fema.maracai.sgh.medicamento.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MedicamentoForm {
	@NotNull
	private String nomeComercial;
	@NotNull
	private String nomeGenerico;
	@NotNull
	private String fabricante;
	@NotNull
	private String concentracao;
	@NotNull
	private String unidadeMedida;
}
