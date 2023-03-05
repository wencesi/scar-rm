package com.wic.app.scar.reporting.scheduler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wic.app.scar.reporting.model.Planificador;
import com.wic.app.scar.reporting.service.ReportService;
import com.wic.app.scar.reporting.service.ExcelGeneratorService;

@Component
public class ReportScheduler {
	private static final Logger log = LoggerFactory.getLogger(ReportScheduler.class);

	@Autowired
	ReportService homeService;
	@Autowired
	private ExcelGeneratorService excelGeneratorService;
	
	@Scheduled(fixedDelay = 15000)
	public void executeInformeOffile() throws IOException {
		Iterable<Planificador> plans = homeService.findAllOfflinePending();
		for (Planificador plan : plans) {
			if (plan.getTipo().equals("inf")) {
				log.info("Ejecutandox informe offline "+plan.getCodigo());
			} else if (plan.getTipo().equals("grp")) {
				log.info("Ejecutando grupo offline "+plan.getCodigo());
			} else if (plan.getTipo().equals("qry")) {
				log.info("Ejecutando query offline "+plan.getCodigo());				
			}
			excelGeneratorService.generateExcelFromPlan(plan);			
		}
	}
}
