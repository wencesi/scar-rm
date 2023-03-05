package com.wic.app.scar.reporting.helper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wic.app.scar.reporting.model.Planificador;

public class ReportExecutorHelper {

	Logger logger = LoggerFactory.getLogger(ReportExecutorHelper.class);	
	
	public static String getFullName(Planificador plan, String filename) throws IOException {
		File dataDir = new File(".", "/scar-data/");
		if(!dataDir.exists()) {
			dataDir.mkdir();
		}
		String fileLocation = new File(".").getAbsolutePath()
				+"/scar-data/"
				+ filename;				
		return fileLocation;
	}
	
	public static String getFullName(String fileName) throws IOException {
		String fileLocation = new File(".").getAbsolutePath()
				+"/scar-data/"
				+ fileName;
		return fileLocation;
	}	
	
	
	public static String getFileName(Planificador plan) throws IOException {
		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString() 
				+ "_" + System.currentTimeMillis() + "_"				
				+ "_" + plan.getUsuario() + "_"
				+ plan.getTipo() + "_" + plan.getCodigo() + ".xlsx";				
	}	
	
}
