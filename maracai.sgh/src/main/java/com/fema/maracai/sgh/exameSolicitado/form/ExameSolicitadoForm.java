package com.fema.maracai.sgh.exameSolicitado.form;

import com.fema.maracai.sgh.exame.model.Exame;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExameSolicitadoForm {
	@NotNull
    private String descricao;
    @NotNull
    private Exame exame;
    @NotNull
    private Paciente paciente;
    @NotNull
    private Medico medico;
}
