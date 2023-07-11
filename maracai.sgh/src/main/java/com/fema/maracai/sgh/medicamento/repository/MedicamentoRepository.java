package com.fema.maracai.sgh.medicamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fema.maracai.sgh.medicamento.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
