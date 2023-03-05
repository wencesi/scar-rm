package com.wic.app.scar.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wic.app.scar.reporting.model.Planificador;

public interface PlanificadorRepository extends JpaRepository<Planificador, Long> {
	@Query("SELECT u FROM Planificador u WHERE u.estado = 'pending' and u.modo='offline' order by u.id asc")
	Iterable<Planificador> findAllOfflinePending();
}
