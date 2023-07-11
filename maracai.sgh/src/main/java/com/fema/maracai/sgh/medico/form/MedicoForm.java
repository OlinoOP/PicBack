package com.fema.maracai.sgh.medico.form;

import com.fema.maracai.sgh.usuario.model.Usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MedicoForm {
	@NotNull
	@Size(max=20)
	private String crm;
	@NotNull
	@Size(max=20)
	private String telefone;
	@NotNull
	private Usuario usuario;
}
