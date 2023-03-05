package com.wic.app.scar.reporting.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wic.app.scar.reporting.model.Columna;
import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Grupo;
import com.wic.app.scar.reporting.model.Informe;
import com.wic.app.scar.reporting.model.Parametro;
import com.wic.app.scar.reporting.model.Planificador;
import com.wic.app.scar.reporting.repository.ColumnaRepository;
import com.wic.app.scar.reporting.repository.ConsultaRepository;
import com.wic.app.scar.reporting.repository.GrupoRepository;
import com.wic.app.scar.reporting.repository.InformeRepository;
import com.wic.app.scar.reporting.repository.ParametroRepository;
import com.wic.app.scar.reporting.repository.PlanificadorRepository;
import com.wic.app.scar.reporting.service.ExcelGeneratorService;
import com.wic.app.scar.reporting.service.ReportService;

@Service
public class DefaultReportService implements ReportService {

	@Autowired
	private InformeRepository informeRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private ConsultaRepository consultaRepository;
	@Autowired
	private ColumnaRepository columnaRepository;
	@Autowired
	private ParametroRepository parametroRepository;
	@Autowired
	private PlanificadorRepository planificadorRepository;
	@Autowired
	private ExcelGeneratorService excelGeneratorService;
	
	@Override
	public Optional<Informe> findInformeById(Long id) {
		return informeRepository.findById(id);
	}

	public Iterable<Informe> findAllInformes() {
		return informeRepository.findAll();
	}

	@Override
	public Optional<Grupo> findGrupoById(Long id) {
		return grupoRepository.findById(id);
	}

	@Override
	public Optional<Consulta> findConsultaById(Long id) {
		return consultaRepository.findById(id);
	}

	@Override
	public Optional<Planificador> findPlanificadorById(Long id) {
		return planificadorRepository.findById(id);
	}

	@Override
	public Iterable<Planificador> findAllPlanificadores() {
		return planificadorRepository.findAll();
	}

	@Override
	public Iterable<Planificador> findAllOfflinePending() {
		return planificadorRepository.findAllOfflinePending();
	}
	
	@Override
	public Optional<Columna> findColumnaById(Long id) {
		return columnaRepository.findById(id);
	}
	
	@Override
	public Optional<Parametro> findParametroById(Long id) {
		return parametroRepository.findById(id);
	}

	/**
	 * Ejecuta informe de forma online, es decir que lo genera inmediatamente
	 */
	@Override
	public void executeInformeOnline(Informe informe) throws IOException {
		Planificador plan = executeInforme(informe, "online");
		excelGeneratorService.generateExcelFromPlan(plan);
	}

	/**
	 * Ejecuta informe de forma offline, es decir que tan sólo anota la petición en
	 * el planificador pero lo ejecutará un shceduler
	 */
	@Override
	public void executeInformeOffile(Informe informe) {
		executeInforme(informe, "offline");
	}

	/**
	 * Ejecuta grupo de forma online, es decir que lo genera inmediatamente
	 */
	@Override
	public void executeGrupoOnline(Grupo grupo) throws IOException {
		Planificador plan = executeGrupo(grupo, "online");
		excelGeneratorService.generateExcelFromPlan(plan);
	}

	/**
	 * Ejecuta grupo de forma offline, es decir que tan sólo anota la petición en el
	 * planificador pero lo ejecutará un shceduler
	 */
	@Override
	public void executeGrupoOffline(Grupo grupo) {
		executeGrupo(grupo, "offline");
	}

	/**
	 * Ejecuta consulta de forma online, es decir que lo genera inmediatamente
	 */
	@Override
	public void executeConsultaOnline(Consulta consulta) throws IOException {
		Planificador plan = executeConsulta(consulta, "online");
		excelGeneratorService.generateExcelFromPlan(plan);
	}

	/**
	 * Ejecuta consulta de forma offline, es decir que tan sólo anota la petición en
	 * el planificador pero lo ejecutará un shceduler
	 */
	@Override
	public void executeConsultaOffline(Consulta consulta) {
		executeConsulta(consulta, "offline");
	}

	@Override
	public Consulta save(Consulta consulta) {
		consultaRepository.save(consulta);
		return consulta;
	}
	
	@Override
	public Columna save(Columna columna) {
		columnaRepository.save(columna);
		return columna;
	}
	
	@Override
	public Parametro save(Parametro parametro) {
		parametroRepository.save(parametro);
		return parametro;
	}
	
	@Override
	public void delete(Columna columna) {
		Consulta consulta = columna.getConsulta();
		for(int i=0;i<consulta.getColumnas().size();i++) {
			if(consulta.getColumnas().get(i).getId().equals(columna.getId())) {
				consulta.getColumnas().remove(i);
			}
		}
		consultaRepository.save(consulta);
	}
	
	@Override
	public void delete(Parametro parametro) {
		Consulta consulta = parametro.getConsulta();
		for(int i=0;i<consulta.getParametros().size();i++) {
			if(consulta.getParametros().get(i).getId().equals(parametro.getId())) {
				consulta.getParametros().remove(i);
			}
		}
		consultaRepository.save(consulta);
	}

	private Planificador executeInforme(Informe informe, String modo) {
		Planificador plan = execute(modo, "inf");
		plan.setCodigo(informe.getCodigo());
		planificadorRepository.save(plan);
		return plan;
	}

	private Planificador executeGrupo(Grupo grupo, String modo) {
		Planificador plan = execute(modo, "grp");
		plan.setCodigo(grupo.getCodigo());
		planificadorRepository.save(plan);
		return plan;
	}

	private Planificador executeConsulta(Consulta consulta, String modo) {
		Planificador plan = execute(modo, "qry");
		plan.setCodigo(consulta.getCodigo());
		planificadorRepository.save(plan);
		return plan;
	}

	private Planificador execute(String modo, String tipo) {
		Planificador plan = new Planificador();
		plan.setEstado("pending");
		plan.setTipo(tipo);
		plan.setUsuario("wences");
		plan.setFechaPeticion(LocalDateTime.now());
		plan.setModo(modo);
		return plan;
	}	
	
}
