package com.wic.app.scar.reporting.service;

import java.io.File;
import java.io.IOException;

import com.wic.app.scar.reporting.model.Planificador;

public interface ExcelGeneratorService {
	File generateExcelFromPlan(Planificador plan) throws IOException;
}
