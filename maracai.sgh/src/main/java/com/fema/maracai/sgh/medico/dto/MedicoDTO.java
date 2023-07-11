package com.fema.maracai.sgh.medico.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.usuario.dto.UsuarioDTO;

import lombok.Getter;

@Getter
public class MedicoDTO {

	private Long id;
	private UsuarioDTO usuario;
	private String crm;
	private String telefone;
	
	public MedicoDTO(Medico medico) {
		id = medico.getId();
		usuario = new UsuarioDTO(medico.getUsuario());
		crm = medico.getCrm();
		telefone = medico.getTelefone();
	}
	
	public static List<MedicoDTO> converter(List<Medico> medicos){
		medicos = medicos.stream().sorted((m1, m2) -> m1.getUsuario().getNome().compareTo(m2.getUsuario().getNome())).collect(Collectors.toList());
		return medicos.stream().map(MedicoDTO::new).collect(Collectors.toList());
	}
}
