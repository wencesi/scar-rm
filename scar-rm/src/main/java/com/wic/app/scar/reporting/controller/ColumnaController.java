package com.wic.app.scar.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wic.app.scar.reporting.model.Columna;
import com.wic.app.scar.reporting.model.Consulta;

import jakarta.validation.Valid;

@Controller
public class ColumnaController extends SCARController {

	/**
	 * Formulario para dar de alta nueva columna
	 */
	@GetMapping({ "/informe/grupo/consulta/{id}/columna/new" })
	public String newColumna(Model model, @PathVariable("id") long id) {
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		model.addAttribute("columna", new Columna());
		model.addAttribute("consulta", consulta);
		return "add-columna";
	}

	/**
	 * Alta de nueva columna
	 */
	@PostMapping({ "/informe/grupo/consulta/{id}/columna/add" })
	public String addColumna(@Valid Columna fColumna, BindingResult result, Model model, @PathVariable("id") long id) {
		if (result.hasErrors()) {
			return "add-columna";
		}
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));

		Columna columna = new Columna();
		columna.setHeader(fColumna.getHeader());
		columna.setSql_name(fColumna.getSql_name());
		columna.setOrden(fColumna.getOrden());
		columna.setConsulta(consulta);
		;
		consulta.getColumnas().add(columna);
		reportService.save(consulta);
		return "redirect:/informe/grupo/consulta/edit/" + consulta.getId();
	}

	/**
	 * Formulario para editar una columna existente
	 */
	@GetMapping({ "/informe/grupo/consulta/columna/edit/{id}" })
	public String editColumna(Model model, @PathVariable("id") long id) {
		Columna columna = reportService.findColumnaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Columna Id:" + id));
		model.addAttribute("columna", columna);
		return "update-columna";
	}

	/**
	 * Actualización de una columna existente
	 */
	@PostMapping("/update/columna/{id}")
	public String updateColumna(@PathVariable("id") long id, @Valid Columna fColumna, BindingResult result,
			Model model) {
		/*
		 * if (consulta.hasErrors()) { consulta.setId(id); return "update-consulta"; }
		 */
		Columna columna = reportService.findColumnaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Columna Id:" + id));

		columna.setSql_name(fColumna.getSql_name());
		columna.setHeader(fColumna.getHeader());
		columna.setOrden(fColumna.getOrden());

		reportService.save(columna);
		return "redirect:/informe/grupo/consulta/edit/" + columna.getConsulta().getId();
	}

	/**
	 * Borrado de una columna
	 */
	@GetMapping("/delete/columna/{id}")
	public String deleteColumna(@PathVariable("id") long id, Model model) {
		Columna columna = reportService.findColumnaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Columna Id:" + id));
		reportService.delete(columna);
		return "redirect:/informe/grupo/consulta/edit/" + columna.getConsulta().getId();
	}
}
