package com.fema.maracai.sgh.paciente.model;

import java.time.LocalDate;

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
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	@Column
	private String cpf;
	@Column
	private String sexo;
	@Column
	private String endereco;
	@Column
	private String cidade;
	@Column
	private String estado;
	@Column
	private String telefone;
}
