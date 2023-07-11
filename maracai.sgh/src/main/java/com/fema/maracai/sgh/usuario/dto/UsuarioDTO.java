package com.fema.maracai.sgh.usuario.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.usuario.model.Usuario;

import lombok.Getter;

@Getter
public class UsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String permissao;
	
	public UsuarioDTO(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		cpf = usuario.getCpf();
		permissao = usuario.getPermissao();	
	}
	
	public static List<UsuarioDTO> converter(List<Usuario> usuarios){
		usuarios = usuarios.stream().sorted((u1, u2) -> u1.getNome().compareTo(u2.getNome())).collect(Collectors.toList());
		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
}
