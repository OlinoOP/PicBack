package com.fema.maracai.sgh.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.usuario.dto.UsuarioDTO;
import com.fema.maracai.sgh.usuario.form.UsuarioForm;
import com.fema.maracai.sgh.usuario.form.UsuarioFormSemSenha;
import com.fema.maracai.sgh.usuario.model.Usuario;
import com.fema.maracai.sgh.usuario.repository.UsuarioRepository;
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarUsuario(Long id) {
		Optional<Usuario> user = this.usuarioRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new ObjectNotFoundException("Usuario não encontrado");
	}
	
	public List<UsuarioDTO> buscarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = UsuarioDTO.converter(usuarios);
		return usuariosDTO;
	}
	
	@Transactional
	public UsuarioDTO inserir(UsuarioForm form) {
		Usuario user = criaUsuarioComForm(form);
		this.usuarioRepository.save(user);
		return new UsuarioDTO(user);
	}

	private Usuario criaUsuarioComForm(UsuarioForm form) {
		Usuario user = Usuario.builder()
				.nome(form.getNome())
				.senha(form.getSenha())
				.cpf(form.getCpf())
				.permissao(form.getPermissao())
				.build();
		return user;
	}

	@Transactional
	public UsuarioDTO atualizar(Long id, UsuarioFormSemSenha formUsuario) {
		Usuario user = this.buscarUsuario(id);
		if(user.getId().equals(id)) {
			user.setCpf(formUsuario.getCpf());
			user.setNome(formUsuario.getNome());
			user.setPermissao(formUsuario.getPermissao());
			this.usuarioRepository.save(user);
		}
		return new UsuarioDTO(user);
	}

	@Transactional
	public UsuarioDTO deletar(Long id) {
		Usuario user = this.buscarUsuario(id);
		if(user.getId().equals(id)) {
			this.usuarioRepository.delete(user);
		}
		return new UsuarioDTO(user);
	}

}
