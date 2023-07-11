package com.fema.maracai.sgh.medico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.medico.dto.MedicoDTO;
import com.fema.maracai.sgh.medico.form.MedicoForm;
import com.fema.maracai.sgh.medico.form.MedicoFormSemSenha;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.medico.repository.MedicoRepository;
import com.fema.maracai.sgh.usuario.model.Usuario;
import com.fema.maracai.sgh.usuario.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public Medico buscarMedico(Long id) {
		Optional<Medico> medico = this.medicoRepository.findById(id);
		if(medico.isPresent()) {
			return medico.get();
		}
		throw new ObjectNotFoundException("Medico não encontrado");
	}
	
	public List<MedicoDTO> buscarTodos() {
		List<Medico> medicos = medicoRepository.findAll();
		List<MedicoDTO> medicosDTO = MedicoDTO.converter(medicos);
		return medicosDTO;
	}
	
	private Usuario buscarUsuario(Long id) {
		Optional<Usuario> user = this.usuarioRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new ObjectNotFoundException("Usuario não encontrado");
	}
	@Transactional
	public MedicoDTO inserir(MedicoForm form) {
		Usuario user = buscarUsuario(form.getUsuario().getId());
		Medico medico = criaMedicoComForm(form);
		medico.setUsuario(user);
		this.medicoRepository.save(medico);
		return new MedicoDTO(medico);
	}

	private Medico criaMedicoComForm(MedicoForm form) {
		Medico medico = Medico.builder().crm(form.getCrm()).telefone(form.getTelefone()).build();
		return medico;
	}
	
	@Transactional
	public MedicoDTO atualizar(Long id, @Valid MedicoFormSemSenha form) {
		Medico medico = this.buscarMedico(id);
		medico.setCrm(form.getCrm());
		medico.setTelefone(form.getTelefone());
		medico.setId(id);
		this.medicoRepository.save(medico);
		return new MedicoDTO(medico);
	}
	@Transactional
	public MedicoDTO deletar(Long id) {
		Medico medico = this.buscarMedico(id);
		this.medicoRepository.delete(medico);
		return new MedicoDTO(medico);
	}

}
