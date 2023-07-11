package com.fema.maracai.sgh.prescricao.form;

import com.fema.maracai.sgh.medicamento.model.Medicamento;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PrescricaoForm {
	private Long id;
	@NotNull
	@Size(max=50)
	private String dosagem;
	@NotNull
	@Size(max=50)
	private String frequencia;
	@NotNull
	@Size(max=50)
	private String duracao;
	@NotNull
	private Medicamento medicamento;
	@NotNull
	private Medico medico;
	@NotNull
	private Paciente paciente;
}
