package com.fema.maracai.sgh.internacao.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.internacao.dto.InternacaoDTO;
import com.fema.maracai.sgh.internacao.form.InternacaoForm;
import com.fema.maracai.sgh.internacao.model.Internacao;
import com.fema.maracai.sgh.internacao.repository.InternacaoRepository;
import com.fema.maracai.sgh.leito.service.LeitoService;
import com.fema.maracai.sgh.medico.service.MedicoService;
import com.fema.maracai.sgh.paciente.service.PacienteService;

import jakarta.transaction.Transactional;

@Service
public class InternacaoService {

    @Autowired
    private InternacaoRepository internacaoRepository;

    @Autowired
    private LeitoService leitoService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    public List<InternacaoDTO> listarInternacoes() {
        List<Internacao> internacoes = internacaoRepository.findAll();
        return InternacaoDTO.converter(internacoes);
    }

    public InternacaoDTO buscarInternacao(Long id) {
        Optional<Internacao> optionalInternacao = internacaoRepository.findById(id);
        Internacao internacao = optionalInternacao.orElseThrow(() -> new ObjectNotFoundException("Internação não encontrada"));
        return new InternacaoDTO(internacao);
    }

    @Transactional
    public InternacaoDTO criarInternacao(InternacaoForm form) {
        Internacao internacao = criarInternacaoComForm(form);
        internacaoRepository.save(internacao);
        leitoService.atualizarDisponibilidadeLeito(form.getLeito().getId(), false);
        return new InternacaoDTO(internacao);
    }

    @Transactional
    public InternacaoDTO atualizarInternacao(Long id, InternacaoForm form) {
        Internacao internacao = buscarInternacaoPorId(id);
        atualizarInternacaoComForm(internacao, form);
        return new InternacaoDTO(internacao);
    }

    @Transactional
    public void deletarInternacao(Long id) {
        Internacao internacao = buscarInternacaoPorId(id);
        internacaoRepository.delete(internacao);
    }

    private Internacao criarInternacaoComForm(InternacaoForm form) {
        Internacao internacao = new Internacao();
        internacao.setDataEntrada(LocalDate.now());
        internacao.setDiagnostico(form.getDiagnostico());
        internacao.setLeito(leitoService.buscarLeito(form.getLeito().getId()));
        internacao.setMedico(medicoService.buscarMedico(form.getMedico().getId()));
        internacao.setPaciente(pacienteService.buscarPaciente(form.getPaciente().getId()));
        return internacao;
    }

    private void atualizarInternacaoComForm(Internacao internacao, InternacaoForm form) {
        internacao.setDiagnostico(form.getDiagnostico());
        internacao.setLeito(leitoService.buscarLeito(form.getLeito().getId()));
        internacao.setMedico(medicoService.buscarMedico(form.getMedico().getId()));
        internacao.setPaciente(pacienteService.buscarPaciente(form.getPaciente().getId()));
    }

    private Internacao buscarInternacaoPorId(Long id) {
        Optional<Internacao> optionalInternacao = internacaoRepository.findById(id);
        return optionalInternacao.orElseThrow(() -> new ObjectNotFoundException("Internação não encontrada"));
    }
    
    @Transactional
    public InternacaoDTO fecharInternacao(Long internacaoId) {
        Internacao internacao = buscarInternacaoPorId(internacaoId);
        internacao.setDataSaida(LocalDate.now());
        Long leitoId = internacao.getLeito().getId();
        internacaoRepository.save(internacao);
        leitoService.atualizarDisponibilidadeLeito(leitoId, true);
        return new InternacaoDTO(internacao);
    }
}
