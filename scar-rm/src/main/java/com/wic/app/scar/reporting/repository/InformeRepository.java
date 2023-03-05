package com.wic.app.scar.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wic.app.scar.reporting.model.Informe;


public interface InformeRepository extends JpaRepository<Informe, Long> {
	Informe findByCodigo (String codigo);
}
