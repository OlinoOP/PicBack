package com.fema.maracai.sgh.internacao.form;

import com.fema.maracai.sgh.leito.model.Leito;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InternacaoForm {
	    private String diagnostico;
	    @NotNull
	    private Leito leito;
	    @NotNull
	    private Medico medico;
	    @NotNull
	    private Paciente paciente;
}
