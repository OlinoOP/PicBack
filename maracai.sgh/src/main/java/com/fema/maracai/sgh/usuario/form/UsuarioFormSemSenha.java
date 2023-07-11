package com.fema.maracai.sgh.usuario.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UsuarioFormSemSenha {
	
	@NotNull
	private String nome;
	@NotNull
	@Size(min=11, max=11)
	private String cpf;
	@NotNull
	@Size(max=1)
	private String permissao;
	
	
}
