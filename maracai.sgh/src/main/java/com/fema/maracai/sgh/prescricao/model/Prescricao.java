package com.fema.maracai.sgh.prescricao.model;

import java.time.LocalDate;

import com.fema.maracai.sgh.medicamento.model.Medicamento;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Prescricao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String dosagem;
	@Column
	private String frequencia;
	@Column
	private String duracao;
	@Column(name = "data_prescricao")
	private LocalDate dataPrescricao;
	@JoinColumn
	@ManyToOne
	private Medicamento medicamento;
	@JoinColumn
	@ManyToOne
	private Medico medico;
	@JoinColumn
	@ManyToOne
	private Paciente paciente;
}
