package com.fema.maracai.sgh.exame.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.exame.model.Exame;

import lombok.Getter;

@Getter
public class ExameDTO {
	private Long id;
	private String descricao;
	private String tipo;
	
	public ExameDTO(Exame exame) {
		id = exame.getId();
		descricao = exame.getDescricao();
		tipo = exame.getTipo();
	}
	
	public static List<ExameDTO> converter(List<Exame> exames){
		return exames.stream().map(ExameDTO::new).collect(Collectors.toList());
	}
}
