package com.fema.maracai.sgh.exame.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExameForm {
	@NotNull
	private String descricao;
	@NotNull
	private String tipo;
}
