package com.fema.maracai.sgh.dispensacaoMedicamento.model;

import java.time.LocalDate;

import com.fema.maracai.sgh.estoqueMedicamento.model.EstoqueMedicamento;
import com.fema.maracai.sgh.usuario.model.Usuario;

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
@Table(name = "dispensacaomedicamento")
public class DispensacaoMedicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "data_dispensacao")
	private LocalDate dataDispensacao;
	@Column(name = "quantidade_dispensada")
	private Long quantidadeDispensada;
	@ManyToOne
	@JoinColumn
	private EstoqueMedicamento estoqueMedicamento;
	@ManyToOne
	@JoinColumn
	private Usuario usuario;
}
