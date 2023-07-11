package com.fema.maracai.sgh.dispensacaoMedicamento.form;

import com.fema.maracai.sgh.estoqueMedicamento.model.EstoqueMedicamento;
import com.fema.maracai.sgh.usuario.model.Usuario;

import lombok.Getter;

@Getter
public class DispensacaoMedicamentoForm {
	private String dataDispensacao;
	private Long quantidadeDispensada;
	private EstoqueMedicamento estoqueMedicamento;
	private Usuario usuario;
}
