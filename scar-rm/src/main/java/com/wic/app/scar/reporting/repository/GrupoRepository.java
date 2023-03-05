package com.wic.app.scar.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wic.app.scar.reporting.model.Grupo;


public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	Grupo findByCodigo (String codigo);
}
