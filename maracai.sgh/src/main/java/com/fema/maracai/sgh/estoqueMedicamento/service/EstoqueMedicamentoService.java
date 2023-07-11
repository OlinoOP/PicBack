package com.fema.maracai.sgh.estoqueMedicamento.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fema.maracai.sgh.dispensacaoMedicamento.model.DispensacaoMedicamento;
import com.fema.maracai.sgh.dispensacaoMedicamento.repository.DispensacaoMedicamentoRepository;
import com.fema.maracai.sgh.estoqueMedicamento.dto.EstoqueMedicamentoDTO;
import com.fema.maracai.sgh.estoqueMedicamento.form.EstoqueMedicamentoForm;
import com.fema.maracai.sgh.estoqueMedicamento.model.EstoqueMedicamento;
import com.fema.maracai.sgh.estoqueMedicamento.repository.EstoqueMedicamentoRepository;
import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.medicamento.model.Medicamento;
import com.fema.maracai.sgh.medicamento.repository.MedicamentoRepository;
import com.fema.maracai.sgh.usuario.model.Usuario;
import com.fema.maracai.sgh.usuario.repository.UsuarioRepository;

@Service
public class EstoqueMedicamentoService {

	@Autowired
	private EstoqueMedicamentoRepository etqMedicamentoRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DispensacaoMedicamentoRepository diMedRepository;

	private Medicamento buscarMedicamento(Long id) {
		Optional<Medicamento> medicamento = this.medicamentoRepository.findById(id);
		if(medicamento.isPresent()) {
			return medicamento.get();
		}
		throw new ObjectNotFoundException("Medicamento não encontrado");
	}
	
	private EstoqueMedicamento buscarEstoqueMedicamento(Long id) {
		Optional<EstoqueMedicamento> etqMedicamento = this.etqMedicamentoRepository.findById(id);
		if(etqMedicamento.isPresent()) {
			return etqMedicamento.get();
		}
		throw new ObjectNotFoundException("Estoque de Medicamento não encontrado");
	}
	
	public List<EstoqueMedicamentoDTO> buscarTodos() {
		List<EstoqueMedicamento> etqMedicamentos = this.etqMedicamentoRepository.findAll();
		List<EstoqueMedicamentoDTO> etqMedicamentosDTO = EstoqueMedicamentoDTO.converter(etqMedicamentos);
		return etqMedicamentosDTO;
	}
	
	@Transactional
	public EstoqueMedicamentoDTO inserir(EstoqueMedicamentoForm form) {
		Medicamento medicamento = buscarMedicamento(form.getMedicamento().getId());
		EstoqueMedicamento etqMed = this.criaEstoqueMedicamentoComForm(form);
		etqMed.setMedicamento(medicamento);
		this.etqMedicamentoRepository.save(etqMed);
		return new EstoqueMedicamentoDTO(etqMed);
	}
	
	private EstoqueMedicamento criaEstoqueMedicamentoComForm(EstoqueMedicamentoForm form) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return EstoqueMedicamento.builder()
				
				.dataValidade(LocalDate.parse(form.getDataValidade(), dateFormatter))
				.lote(form.getLote())
				.quantidade(form.getQuantidade()).build();
	}

	@Transactional
	public EstoqueMedicamentoDTO atualizar(Long idEstoque, Long idUsuario, EstoqueMedicamentoForm form) {
		EstoqueMedicamento etqMed = this.buscarEstoqueMedicamento(idEstoque);
		Medicamento medicamento = this.buscarMedicamento(form.getMedicamento().getId());
		DispensacaoMedicamento diMed = null;
		if(this.verificaRetirada(etqMed, form)) {
			Usuario user = this.buscarUsuario(idUsuario);
			diMed = DispensacaoMedicamento.builder()
					.dataDispensacao(LocalDate.now())
					.quantidadeDispensada(etqMed.getQuantidade()-form.getQuantidade())
					.usuario(user)
					.build();
		};
		etqMed = criaEstoqueMedicamentoComForm(form);
		etqMed.setId(idEstoque);
		etqMed.setMedicamento(medicamento);
		this.etqMedicamentoRepository.save(etqMed);
		if(diMed!=null) {
			diMed.setEstoqueMedicamento(etqMed);
			this.diMedRepository.save(diMed);
		}
		return new EstoqueMedicamentoDTO(etqMed);
	}

	private Usuario buscarUsuario(Long id) {
		Optional<Usuario> user = this.usuarioRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new ObjectNotFoundException("Usuario não encontrado");
	}

	private Boolean verificaRetirada(EstoqueMedicamento etqMed, EstoqueMedicamentoForm form) {
		if(form.getQuantidade()<etqMed.getQuantidade()) {
			return true;
		}
		return false;
	}

	@Transactional
	public EstoqueMedicamentoDTO deletar(Long id) {
		EstoqueMedicamento etqMed = this.buscarEstoqueMedicamento(id);
		this.etqMedicamentoRepository.delete(etqMed);
		return new EstoqueMedicamentoDTO(etqMed);
	}
}
