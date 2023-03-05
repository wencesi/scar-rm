package com.wic.app.scar.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wic.app.scar.reporting.model.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	Consulta findByCodigo (String codigo);
}
