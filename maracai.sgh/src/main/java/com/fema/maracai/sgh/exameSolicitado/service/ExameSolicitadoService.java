package com.fema.maracai.sgh.exameSolicitado.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fema.maracai.sgh.exame.model.Exame;
import com.fema.maracai.sgh.exame.service.ExameService;
import com.fema.maracai.sgh.exameSolicitado.dto.ExameSolicitadoDTO;
import com.fema.maracai.sgh.exameSolicitado.form.ExameSolicitadoForm;
import com.fema.maracai.sgh.exameSolicitado.model.ExameSolicitado;
import com.fema.maracai.sgh.exameSolicitado.repository.ExameSolicitadoRepository;
import com.fema.maracai.sgh.exception.custom.ObjectNotFoundException;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.medico.service.MedicoService;
import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.paciente.service.PacienteService;

@Service
public class ExameSolicitadoService {

    @Autowired
    private ExameSolicitadoRepository exameSolicitadoRepository;

    @Autowired
    private ExameService exameService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    public List<ExameSolicitadoDTO> buscarTodosExames() {
        List<ExameSolicitado> exames = exameSolicitadoRepository.findAll();
        return ExameSolicitadoDTO.converter(exames);
    }

    public ExameSolicitadoDTO criarExame(ExameSolicitadoForm form) {
        Exame exame = exameService.buscarExame(form.getExame().getId());
        Paciente paciente = pacienteService.buscarPaciente(form.getPaciente().getId());
        Medico medico = medicoService.buscarMedico(form.getMedico().getId());
        ExameSolicitado exameSolicitado = criaExameSolicitadoComForm(form);
        exameSolicitado.setExame(exame);
        exameSolicitado.setPaciente(paciente);
        exameSolicitado.setMedico(medico);
        exameSolicitadoRepository.save(exameSolicitado);
        return new ExameSolicitadoDTO(exameSolicitado);
    }

    private ExameSolicitado criaExameSolicitadoComForm(ExameSolicitadoForm form) {
        return ExameSolicitado.builder()
                .descricao(form.getDescricao())
                .dataExame(LocalDate.now())
                .build();
    }

    public ExameSolicitadoDTO atualizarExame(Long id, ExameSolicitadoForm form) {
        ExameSolicitado exameSolicitado = buscarExameSolicitado(id);
        Exame exame = exameService.buscarExame(form.getExame().getId());
        Paciente paciente = pacienteService.buscarPaciente(form.getPaciente().getId());
        Medico medico = medicoService.buscarMedico(form.getMedico().getId());
        exameSolicitado = criaExameSolicitadoComForm(form);
        exameSolicitado.setId(id);
        exameSolicitado.setExame(exame);
        exameSolicitado.setPaciente(paciente);
        exameSolicitado.setMedico(medico);
        exameSolicitadoRepository.save(exameSolicitado);
        return new ExameSolicitadoDTO(exameSolicitado);
    }

    public void deletarExame(Long id) {
        ExameSolicitado exameSolicitado = buscarExameSolicitado(id);
        exameSolicitadoRepository.delete(exameSolicitado);
    }

    private ExameSolicitado buscarExameSolicitado(Long id) {
        Optional<ExameSolicitado> exameSolicitado = exameSolicitadoRepository.findById(id);
        if (exameSolicitado.isPresent()) {
            return exameSolicitado.get();
        }
        throw new ObjectNotFoundException("Exame solicitado não encontrado");
    }
}

