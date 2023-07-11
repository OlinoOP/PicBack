package com.fema.maracai.sgh.leito.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.leito.model.Leito;

import lombok.Getter;

@Getter
public class LeitoDTO {
	private Long id;
	private Long numeroSala;
	private Boolean disponibilidade;
	
	public LeitoDTO(Leito leito) {
		id = leito.getId();
		numeroSala = leito.getNumeroSala();
		disponibilidade = leito.getDisponibilidade();
	}
	
	public static List<LeitoDTO> converter(List<Leito> leitos){
		leitos = leitos.stream().sorted((l1, l2) -> l1.getNumeroSala().compareTo(l2.getNumeroSala())).collect(Collectors.toList());
		return leitos.stream().map(LeitoDTO::new).collect(Collectors.toList());
	}
}
