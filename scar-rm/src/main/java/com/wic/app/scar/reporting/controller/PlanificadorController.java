package com.wic.app.scar.reporting.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wic.app.scar.reporting.helper.ReportExecutorHelper;
import com.wic.app.scar.reporting.model.Planificador;

@Controller
public class PlanificadorController extends SCARController{

	/**
	 * Planificador
	 */
	@GetMapping({ "/planificador" })
	public String planificador(Model model) {
		model.addAttribute("plans", reportService.findAllPlanificadores());
		return "planificador";
	}

	/**
	 * Descarga de un archivo
	 */
	@GetMapping({ "/planificador/download" })
	public ResponseEntity<Resource> downloadFile(@RequestParam(value = "id", required = true) long id)
			throws IOException {
		Planificador plan = reportService.findPlanificadorById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Planificador Id:" + id));

		InputStreamResource file = new InputStreamResource(
				new FileInputStream(ReportExecutorHelper.getFullName(plan.getFichero())));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + plan.getFichero())
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
}
