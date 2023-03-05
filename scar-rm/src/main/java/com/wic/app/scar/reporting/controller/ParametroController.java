package com.wic.app.scar.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Parametro;

import jakarta.validation.Valid;

@Controller
public class ParametroController extends SCARController {


	/**
	 * Formulario para dar de alta nuevo parámetro
	 */
	@GetMapping({ "/informe/grupo/consulta/{id}/parametro/new" })
    public String newParametro(Model model, @PathVariable("id") long id) {
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		model.addAttribute("parametro",new Parametro());
		model.addAttribute("consulta", consulta);		
        return "add-parametro";
    }

	/**
	 * Alta de nuevo parámetro
	 */
	@PostMapping({ "/informe/grupo/consulta/{id}/parametro/add"})
    public String addParametro(@Valid Parametro fParametro, BindingResult result, Model model,@PathVariable("id") long id) {
        if (result.hasErrors()) {
            return "add-parametro";
        }
		Consulta consulta = reportService.findConsultaById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Consulta Id:" + id));
		
		Parametro parametro = new Parametro ();
		parametro.setCodigo(fParametro.getCodigo());
		parametro.setValor(fParametro.getValor());
		parametro.setOrden(fParametro.getOrden());
		parametro.setConsulta(consulta);
		
		consulta.getParametros().add(parametro);
		reportService.save(consulta);
		return "redirect:/informe/grupo/consulta/edit/" + consulta.getId();
    }
	
	/**
	 * Formulario para editar un parámetro existente
	 */
	@GetMapping({ "/informe/grupo/consulta/parametro/edit/{id}" })
	public String editParametro(Model model, @PathVariable("id") long id) {
		Parametro parametro = reportService.findParametroById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Parametro Id:" + id));
		model.addAttribute("parametro", parametro);
		return "update-parametro";
	}

	/**
	 * Actualización de un parámetro existente
	 */
	@PostMapping("/update/parametro/{id}")
	public String udpateParametro(@PathVariable("id") long id, @Valid Parametro fParametro, BindingResult result,
			Model model) {
		/*
		 * if (consulta.hasErrors()) { consulta.setId(id); return "update-consulta"; }
		 */
		Parametro parametro = reportService.findParametroById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Parametro Id:" + id));

		parametro.setCodigo(fParametro.getCodigo());
		parametro.setValor(fParametro.getValor());
		parametro.setOrden(fParametro.getOrden());

		reportService.save(parametro);
		return "redirect:/informe/grupo/consulta/edit/" + parametro.getConsulta().getId();
	}
	
	/**
	 * Borrado de un parámetro
	 */
	@GetMapping("/delete/parametro/{id}")
	public String deleteParametro(@PathVariable("id") long id, Model model) {
		Parametro parametro = reportService.findParametroById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Parametro Id:" + id));
		reportService.delete(parametro);
		return "redirect:/informe/grupo/consulta/edit/" + parametro.getConsulta().getId();
	}	
}
