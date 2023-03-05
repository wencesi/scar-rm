package com.wic.app.scar.reporting.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Grupo;

import jakarta.validation.Valid;

@Controller
public class ConsultaController extends SCARController {

	/**
	 * Lista de todas las consultas de un grupo de un informe
	 */
	@GetMapping({ "/informe/grupo/{id}" })
	public String consultas(Model model, @PathVariable("id") long id) {
		Grupo grupo = reportService.findGrupoById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Grupo Id:" + id));
		model.addAttribute("grupo", grupo);
		return "consultas";
	}

	/**
	 * Edición de una consulta existente
	 */
	@GetMapping({ "/informe/grupo/consulta/edit/{id}" })
	public String showUpdateFormConsulta(Model model, @PathVariable("id") long id) {
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		model.addAttribute("consulta", consulta);
		return "update-consulta";
	}

	@PostMapping("/update/consulta/{id}")
	public String updateConsulta(@PathVariable("id") long id, @Valid Consulta fConsulta, BindingResult result,
			Model model) {
		/*
		if (consulta.hasErrors()) {
			consulta.setId(id);
			return "update-consulta";
		}
		*/		
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));		
		
		consulta.setCodigo(fConsulta.getCodigo());
		consulta.setNombre(fConsulta.getNombre());
		consulta.setDescripcion(fConsulta.getDescripcion());
		consulta.setOrden(fConsulta.getOrden());
		consulta.setSql(fConsulta.getSql());
		
		reportService.save(consulta);
		return "redirect:/informe/grupo/"+consulta.getGrupo().getId();
	}

	@PostMapping("/informe/execute/consulta/online/{id}")
	public String executeConsultaOnline(@PathVariable("id") long id) throws IOException {
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		reportService.executeConsultaOnline(consulta);
		return "redirect:/planificador";
	}

	@PostMapping("/informe/execute/consulta/offline/{id}")
	public String executeConsultaOffline(@PathVariable("id") long id) throws IOException {
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		reportService.executeConsultaOffline(consulta);
		return "redirect:/planificador";
	}
}
