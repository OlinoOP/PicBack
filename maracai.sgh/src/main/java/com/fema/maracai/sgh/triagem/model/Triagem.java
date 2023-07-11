package com.fema.maracai.sgh.triagem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fema.maracai.sgh.paciente.model.Paciente;
import com.fema.maracai.sgh.usuario.model.Usuario;

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
public class Triagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_triagem")
    private LocalDate dataTriagem;
    
    @Column
    private BigDecimal peso;
    
    @Column
    private BigDecimal altura;
    
    @Column
    private String pressao;
    
    @Column
    private BigDecimal temperatura;
    
    @Column
    private String sintomas;
    
    @Column
    private String observacoes;
    
    @Column(name = "status_triagem")
    private Long statusTriagem;
    
    @ManyToOne
    @JoinColumn
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn
    private Usuario usuario;
}
