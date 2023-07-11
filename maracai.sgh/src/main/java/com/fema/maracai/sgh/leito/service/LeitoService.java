package com.fema.maracai.sgh.leito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.leito.dto.LeitoDTO;
import com.fema.maracai.sgh.leito.form.LeitoForm;
import com.fema.maracai.sgh.leito.model.Leito;
import com.fema.maracai.sgh.leito.repository.LeitoRepository;

@Service
public class LeitoService {

	@Autowired
	private LeitoRepository leitoRepository;
	
	public Leito buscarLeito(Long id) {
		Optional<Leito> leito = this.leitoRepository.findById(id);
		if(leito.isPresent()) {
			return leito.get();
		}
		throw new ObjectNotFoundException("Leito não encontrado");
	}
	
	public List<LeitoDTO> buscarTodos() {
		List<Leito> leitos = this.leitoRepository.findAll();
		List<LeitoDTO> leitosDTO = LeitoDTO.converter(leitos);
		return leitosDTO;
	}
	
	@Transactional
	public Object inserir(LeitoForm form) {
		Leito leito = criaLeitoComForm(form);
		this.leitoRepository.save(leito);
		return new LeitoDTO(leito);
	}
	
	private Leito criaLeitoComForm(LeitoForm form) {
		Leito leito = Leito.builder().numeroSala(form.getNumeroSala())
				.disponibilidade(form.getDisponibilidade()).build();
		return leito;
	}

	@Transactional
	public Object atualizar(Long id, LeitoForm form) {
		Leito leito = this.buscarLeito(id);
		if(leito.getId().equals(id)) {
			leito = criaLeitoComForm(form);
			leito.setId(id);
			this.leitoRepository.save(leito);
		}
		return new LeitoDTO(leito);
	}
	
	@Transactional
	public Object deletar(Long id) {
		Leito leito = this.buscarLeito(id);
		if(leito.getId().equals(id)) {
			this.leitoRepository.delete(leito);
		}
		return new LeitoDTO(leito);
	}
	
	@Transactional
    public void atualizarDisponibilidadeLeito(Long leitoId, boolean disponibilidade) {
        Leito leito = buscarLeito(leitoId);
        leito.setDisponibilidade(disponibilidade);
        leitoRepository.save(leito);
    }

}
