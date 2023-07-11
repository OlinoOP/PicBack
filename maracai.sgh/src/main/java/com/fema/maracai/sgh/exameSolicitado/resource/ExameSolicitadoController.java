package com.fema.maracai.sgh.exameSolicitado.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fema.maracai.sgh.exameSolicitado.dto.ExameSolicitadoDTO;
import com.fema.maracai.sgh.exameSolicitado.form.ExameSolicitadoForm;
import com.fema.maracai.sgh.exameSolicitado.service.ExameSolicitadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/examesolicitado")
public class ExameSolicitadoController {

    @Autowired
    private ExameSolicitadoService exameSolicitadoService;

    @GetMapping
    public ResponseEntity<List<ExameSolicitadoDTO>> buscarTodosExames() {
        List<ExameSolicitadoDTO> exames = exameSolicitadoService.buscarTodosExames();
        return ResponseEntity.ok(exames);
    }

    @PostMapping
    public ResponseEntity<ExameSolicitadoDTO> criarExame(@Valid @RequestBody ExameSolicitadoForm form) {
        ExameSolicitadoDTO exame = exameSolicitadoService.criarExame(form);
        return ResponseEntity.status(HttpStatus.OK).body(exame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameSolicitadoDTO> atualizarExame(@PathVariable Long id, @Valid @RequestBody ExameSolicitadoForm form) {
        ExameSolicitadoDTO exame = exameSolicitadoService.atualizarExame(id, form);
        return ResponseEntity.ok(exame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarExame(@PathVariable Long id) {
        exameSolicitadoService.deletarExame(id);
        return ResponseEntity.ok().build();
    }
}

