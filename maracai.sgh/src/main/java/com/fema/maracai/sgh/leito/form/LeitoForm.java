package com.fema.maracai.sgh.leito.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LeitoForm {
	@NotNull
	private Long numeroSala;
	@NotNull
	private Boolean disponibilidade;
}
