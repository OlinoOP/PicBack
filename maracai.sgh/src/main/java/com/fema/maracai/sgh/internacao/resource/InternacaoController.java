package com.fema.maracai.sgh.internacao.resource;

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

import com.fema.maracai.sgh.internacao.dto.InternacaoDTO;
import com.fema.maracai.sgh.internacao.form.InternacaoForm;
import com.fema.maracai.sgh.internacao.service.InternacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    @Autowired
    private InternacaoService internacaoService;

    @GetMapping
    public ResponseEntity<List<InternacaoDTO>> listarInternacoes() {
        List<InternacaoDTO> internacoes = internacaoService.listarInternacoes();
        return ResponseEntity.ok(internacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternacaoDTO> buscarInternacao(@PathVariable Long id) {
        InternacaoDTO internacao = internacaoService.buscarInternacao(id);
        return ResponseEntity.ok(internacao);
    }

    @PostMapping
    public ResponseEntity<InternacaoDTO> criarInternacao(@Valid @RequestBody InternacaoForm form) {
        InternacaoDTO internacao = internacaoService.criarInternacao(form);
        return ResponseEntity.status(HttpStatus.OK).body(internacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternacaoDTO> atualizarInternacao(@PathVariable Long id, @Valid @RequestBody InternacaoForm form) {
        InternacaoDTO internacao = internacaoService.atualizarInternacao(id, form);
        return ResponseEntity.ok(internacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarInternacao(@PathVariable Long id) {
        internacaoService.deletarInternacao(id);
        return ResponseEntity.noContent().build();
    }
}

