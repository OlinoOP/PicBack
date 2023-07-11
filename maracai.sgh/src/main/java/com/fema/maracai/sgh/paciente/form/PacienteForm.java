package com.fema.maracai.sgh.paciente.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PacienteForm {
	
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String dataNascimento;
	@NotNull
	@Size(max=11, min=11)
	private String cpf;
	@Size(max=1)
	private String sexo;
	private String endereco;
	private String cidade;
	private String estado;
	@Size(max = 20)
	private String telefone;
}
