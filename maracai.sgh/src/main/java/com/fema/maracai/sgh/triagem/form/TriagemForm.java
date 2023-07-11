package com.fema.maracai.sgh.triagem.form;

import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.usuario.model.Usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TriagemForm {
	private Long id;
    private String peso;
    private String altura;
    @Size(max = 20)
    private String pressao;
    private String temperatura;
    private String sintomas;
    private String observacoes;
    private Long statusTriagem;
    @NotNull
    private Paciente paciente;
    @NotNull
    private Usuario usuario;
}
