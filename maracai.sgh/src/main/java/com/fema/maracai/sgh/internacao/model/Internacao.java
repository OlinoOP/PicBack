package com.fema.maracai.sgh.internacao.model;

import java.time.LocalDate;

import com.fema.maracai.sgh.leito.model.Leito;
import com.fema.maracai.sgh.medico.model.Medico;
import com.fema.maracai.sgh.paciente.model.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "diagnostico")
    private String diagnostico;

    @ManyToOne
    @JoinColumn
    private Leito leito;

    @ManyToOne
    @JoinColumn
    private Medico medico;

    @ManyToOne
    @JoinColumn
    private Paciente paciente;
}
