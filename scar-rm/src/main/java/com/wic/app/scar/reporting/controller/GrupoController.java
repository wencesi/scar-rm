package com.wic.app.scar.reporting.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wic.app.scar.reporting.model.Grupo;
import com.wic.app.scar.reporting.model.Informe;

@Controller
public class GrupoController extends SCARController{

	/**
	 * Lista de todos los grupos de un informe
	 */
	@GetMapping({ "/informe/{id}" })
	public String grupos(Model model, @PathVariable("id") long id) {
		Informe informe = reportService.findInformeById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Informe Id:" + id));
		model.addAttribute("informe", informe);
		return "grupos";
	}

	@PostMapping("/informe/execute/grupo/online/{id}")
	public String executeGrupoOnline(@PathVariable("id") long id) throws IOException {
		Grupo grupo = reportService.findGrupoById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Grupo Id:" + id));
		reportService.executeGrupoOnline(grupo);
		return "redirect:/planificador";
	}

	@PostMapping("/informe/execute/grupo/offline/{id}")
	public String executeGrupoOffline(@PathVariable("id") long id) throws IOException {
		Grupo grupo = reportService.findGrupoById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Grupo Id:" + id));
		reportService.executeGrupoOffline(grupo);
		return "redirect:/planificador";
	}
}
