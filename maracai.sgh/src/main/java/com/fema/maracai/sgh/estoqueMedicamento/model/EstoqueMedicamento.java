package com.fema.maracai.sgh.estoqueMedicamento.model;

import java.time.LocalDate;

import com.fema.maracai.sgh.medicamento.model.Medicamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "estoquemedicamento")
public class EstoqueMedicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn
	@ManyToOne
	private Medicamento medicamento;
	@Column
	private Long quantidade;
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	@Column
	private String lote;
}
