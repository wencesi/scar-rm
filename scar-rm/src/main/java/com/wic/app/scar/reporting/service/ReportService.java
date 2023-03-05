package com.wic.app.scar.reporting.service;

import java.io.IOException;
import java.util.Optional;

import com.wic.app.scar.reporting.model.Columna;
import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Grupo;
import com.wic.app.scar.reporting.model.Informe;
import com.wic.app.scar.reporting.model.Parametro;
import com.wic.app.scar.reporting.model.Planificador;

public interface ReportService {
	Optional<Informe> findInformeById(Long id);
	Iterable<Informe> findAllInformes();
	Optional<Grupo> findGrupoById(Long id);
	Optional<Consulta> findConsultaById(Long id);
	Optional<Columna> findColumnaById(Long id);	
	Optional<Parametro> findParametroById(Long id);		
	Optional<Planificador> findPlanificadorById(Long id);	
	Iterable<Planificador> findAllPlanificadores();
	Iterable<Planificador> findAllOfflinePending();
	Consulta save (Consulta consulta);
	Columna save (Columna columna);
	Parametro save (Parametro parametro);
	void delete (Columna columna);
	void delete (Parametro parametro);	
	void executeInformeOnline(Informe informe) throws IOException;
	void executeInformeOffile(Informe informe);	
	void executeGrupoOnline(Grupo grupo) throws IOException;
	void executeGrupoOffline(Grupo grupo);
	void executeConsultaOnline(Consulta consulta) throws IOException;
	void executeConsultaOffline(Consulta consulta);	
}
