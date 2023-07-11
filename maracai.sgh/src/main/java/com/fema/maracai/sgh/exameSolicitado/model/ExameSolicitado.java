package com.fema.maracai.sgh.exameSolicitado.model;

import java.time.LocalDate;

import com.fema.maracai.sgh.exame.model.Exame;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "examesolicitado")
public class ExameSolicitado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String descricao;
    
    @Column(name = "data_exame")
    private LocalDate dataExame;
    
    @ManyToOne
    @JoinColumn
    private Exame exame;
    
    @ManyToOne
    @JoinColumn
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn
    private Medico medico;
}

