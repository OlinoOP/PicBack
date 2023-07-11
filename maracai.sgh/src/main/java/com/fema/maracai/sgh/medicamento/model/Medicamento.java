package com.fema.maracai.sgh.medicamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_comercial")
	private String nomeComercial;
	@Column(name = "nome_generico")
	private String nomeGenerico;
	@Column
	private String fabricante;
	@Column
	private String concentracao;
	@Column(name = "unidade_medida")
	private String unidadeMedida;
}
