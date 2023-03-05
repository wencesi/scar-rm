package com.wic.app.scar.reporting.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wic.app.scar.reporting.model.Informe;

@Controller
public class InformeController extends SCARController{
	/**
	 * Lista de todos los informes
	 */
	@GetMapping({ "/informe" })
	public String informes(Model model) {
		model.addAttribute("informes", reportService.findAllInformes());
		return "informes";
	}

	@PostMapping("/informe/execute/online/{id}")
	public String executeInformeOnline(@PathVariable("id") long id) throws IOException {
		Informe informe = reportService.findInformeById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Informe Id:" + id));
		reportService.executeInformeOnline(informe);
		return "redirect:/planificador";
	}

	@PostMapping("/informe/execute/offline/{id}")
	public String executeInformeOffline(@PathVariable("id") long id) throws IOException {
		Informe informe = reportService.findInformeById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Informe Id:" + id));
		reportService.executeInformeOffile(informe);
		return "redirect:/planificador";
	}
}
