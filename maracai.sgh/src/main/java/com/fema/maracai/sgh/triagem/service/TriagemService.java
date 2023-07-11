package com.fema.maracai.sgh.triagem.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.paciente.service.PacienteService;
import com.fema.maracai.sgh.triagem.dto.TriagemDTO;
import com.fema.maracai.sgh.triagem.form.TriagemForm;
import com.fema.maracai.sgh.triagem.model.Triagem;
import com.fema.maracai.sgh.triagem.repository.TriagemRepository;
import com.fema.maracai.sgh.usuario.model.Usuario;
import com.fema.maracai.sgh.usuario.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class TriagemService {

	@Autowired
	private TriagemRepository triagemRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Triagem buscarTriagem(Long id) {
		Optional<Triagem> triagem = triagemRepository.findById(id);
		if (triagem.isPresent()) {
			return triagem.get();
		}
		throw new ObjectNotFoundException("Triagem não encontrada");
	}
	
	public List<TriagemDTO> buscarTodasTriagens() {
		List<Triagem> triagens = triagemRepository.findAll();
		return TriagemDTO.converter(triagens);
	}
	
	@Transactional
	public TriagemDTO inserirTriagem(TriagemForm form) {
		Paciente paciente = pacienteService.buscarPaciente(form.getPaciente().getId());
		Usuario usuario = usuarioService.buscarUsuario(form.getUsuario().getId());
		Triagem triagem = criaTriagemComForm(form);
		triagem.setPaciente(paciente);
		triagem.setUsuario(usuario);
		triagem.setDataTriagem(LocalDate.now());
		triagemRepository.save(triagem);
		return new TriagemDTO(triagem);
	}
	
	private Triagem criaTriagemComForm(TriagemForm form) {
		return Triagem.builder()
				.peso(new BigDecimal(form.getPeso()))
				.altura(new BigDecimal(form.getAltura()))
				.pressao(form.getPressao())
				.temperatura(new BigDecimal(form.getTemperatura()))
				.sintomas(form.getSintomas())
				.observacoes(form.getObservacoes())
				.statusTriagem(form.getStatusTriagem())
				.build();
	}
	
	@Transactional
	public TriagemDTO atualizarTriagem(Long id, TriagemForm form) {
		Triagem triagem = buscarTriagem(id);
		Paciente paciente = pacienteService.buscarPaciente(form.getPaciente().getId());
		Usuario usuario = usuarioService.buscarUsuario(form.getUsuario().getId());
		triagem = criaTriagemComForm(form);
		triagem.setId(id);
		triagem.setPaciente(paciente);
		triagem.setUsuario(usuario);
		triagemRepository.save(triagem);
		return new TriagemDTO(triagem);
	}
	
	@Transactional
	public TriagemDTO deletarTriagem(Long id) {
		Triagem triagem = buscarTriagem(id);
		triagemRepository.delete(triagem);
		return new TriagemDTO(triagem);
	}

}

