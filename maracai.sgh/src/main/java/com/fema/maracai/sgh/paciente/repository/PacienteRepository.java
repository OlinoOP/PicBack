package com.fema.maracai.sgh.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fema.maracai.sgh.paciente.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
