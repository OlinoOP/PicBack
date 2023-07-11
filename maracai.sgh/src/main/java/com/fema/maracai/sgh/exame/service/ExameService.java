package com.fema.maracai.sgh.exame.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.exame.dto.ExameDTO;
import com.fema.maracai.sgh.exame.form.ExameForm;
import com.fema.maracai.sgh.exame.model.Exame;
import com.fema.maracai.sgh.exame.repository.ExameRepository;
import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;

@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;
	

	public Exame buscarExame(Long id) {
		Optional<Exame> exame = this.exameRepository.findById(id);
		if(exame.isPresent()){
			return exame.get();
		}
		throw new ObjectNotFoundException("Exame não encontrado");
	}
	
	public List<ExameDTO> buscarTodos() {
		List<Exame> exames = this.exameRepository.findAll();
		List<ExameDTO> examesDTO = ExameDTO.converter(exames);
		return examesDTO;
	}
	
	@Transactional
	public ExameDTO inserir(ExameForm form) {
		Exame exame = criaExameComForm(form);
		this.exameRepository.save(exame);
		return new ExameDTO(exame);
	}

	private Exame criaExameComForm(ExameForm form) {
		Exame exame = Exame.builder()
				.descricao(form.getDescricao())
				.tipo(form.getTipo())
				.build();
		return exame;
	}
	
	@Transactional
	public ExameDTO atualizar(Long id, ExameForm form) {
		Exame exame = this.buscarExame(id);
		exame = criaExameComForm(form);
		exame.setId(id);
		this.exameRepository.save(exame);
		return new ExameDTO(exame);
	}
	
	@Transactional
	public ExameDTO deletar(Long id) {
		Exame exame = buscarExame(id);
		this.exameRepository.delete(exame);
		return new ExameDTO(exame);
	}

}
