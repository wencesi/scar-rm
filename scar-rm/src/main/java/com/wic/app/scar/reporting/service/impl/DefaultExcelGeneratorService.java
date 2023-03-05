package com.wic.app.scar.reporting.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wic.app.scar.reporting.helper.ReportExecutorHelper;
import com.wic.app.scar.reporting.model.Columna;
import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Grupo;
import com.wic.app.scar.reporting.model.Informe;
import com.wic.app.scar.reporting.model.Planificador;
import com.wic.app.scar.reporting.repository.ConsultaRepository;
import com.wic.app.scar.reporting.repository.GrupoRepository;
import com.wic.app.scar.reporting.repository.InformeRepository;
import com.wic.app.scar.reporting.repository.JdbcRepository;
import com.wic.app.scar.reporting.repository.PlanificadorRepository;
import com.wic.app.scar.reporting.service.ExcelGeneratorService;

@Service
public class DefaultExcelGeneratorService implements ExcelGeneratorService{

	// TODO gestionar excepciones y lanzarlas, cerrar bien los archivos con try
	// chatch, etc

	Logger logger = LoggerFactory.getLogger(DefaultExcelGeneratorService.class);

	@Autowired
	private InformeRepository informeRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private ConsultaRepository consultaRepository;
	@Autowired
	private PlanificadorRepository planificadorRepository;
	@Autowired
	private JdbcRepository jdbcRepository;

	CellStyle headerStype = null;
	CellStyle generalStyle = null;
	CellStyle dateStyle = null;
	CellStyle decimalStyle = null;

	public static final String INF = "inf";
	public static final String GRP = "grp";
	public static final String QRY = "qry";
	public static final String DONE = "done";

	@Override
	public File generateExcelFromPlan(Planificador plan) throws IOException {
		logger.info("ReportExecutorService.execute");
		String filename = ReportExecutorHelper.getFileName(plan);
		String fileLocation = ReportExecutorHelper.getFullName(plan,filename);
		XSSFWorkbook workbook = new XSSFWorkbook();
		initStyles(workbook);
		try {
			switch (plan.getTipo()) {
			case INF:
				executeInf(workbook, plan);
				break;
			case GRP:
				executeGrp(workbook, plan);
				break;
			case QRY:
				executeQry(workbook, plan);
				break;
			}

			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();

		} finally {
			if (workbook != null) {
				workbook.close();
			}
			updatePlan(plan,filename);
		}
		return new File(fileLocation);
	}
	
	private void updatePlan(Planificador plan,String filename) throws IOException{
		plan.setEstado(DONE);
		plan.setFichero(filename);
		plan.setFechaEjecucion(LocalDateTime.now());			
		planificadorRepository.save(plan);		
	}

	private void initStyles(XSSFWorkbook workbook) {
		headerStype = getHeaderCellStyle(workbook);
		generalStyle = getGeneralBodyCellStyle(workbook);
		dateStyle = getDateBodyCellStyle(workbook);
		decimalStyle = getDecimalBodyCellStyle(workbook);
	}

	private XSSFWorkbook executeInf(XSSFWorkbook workbook, Planificador plan) {
		Informe informe = informeRepository.findByCodigo(plan.getCodigo());
		return executeInf(workbook, informe);
	}

	private XSSFWorkbook executeGrp(XSSFWorkbook workbook, Planificador plan) {
		Grupo grupo = grupoRepository.findByCodigo(plan.getCodigo());
		return executeGrp(workbook, grupo);
	}

	private XSSFWorkbook executeQry(XSSFWorkbook workbook, Planificador plan) {
		Consulta consulta = consultaRepository.findByCodigo(plan.getCodigo());
		return executeQry(workbook, consulta);
	}

	private XSSFWorkbook executeInf(XSSFWorkbook workbook, Informe informe) {
		for (Grupo grupo : informe.getGrupos()) {
			workbook = executeGrp(workbook, grupo);
		}
		return workbook;
	}

	private XSSFWorkbook executeGrp(XSSFWorkbook workbook, Grupo grupo) {
		for (Consulta consulta : grupo.getConsultas()) {
			workbook = executeQry(workbook, consulta);
		}
		return workbook;
	}

	private XSSFWorkbook executeQry(XSSFWorkbook workbook, Consulta consulta) {
		logger.info("ReportExecutorService.executeQry: " + consulta.getCodigo());
		List<Map<String, Object>> data = jdbcRepository.executeQry(consulta);

		XSSFSheet sheet = workbook.createSheet(consulta.getCodigo());
		writeHeader(workbook, sheet, consulta);

		int rownum = 1;
		for (Map<String, Object> sql_row : data) {
			Row excel_row = sheet.createRow(rownum++);
			int i = 0;
			for (Columna col : consulta.getColumnas()) {
				Object value = sql_row.get(col.getSql_name());
				createCell(workbook, sheet, excel_row, i++, value);
			}
		}
		return workbook;
	}

	private void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet, Consulta consulta) {
		Row excel_row = sheet.createRow(0);
		int i = 0;
		for (Columna col : consulta.getColumnas()) {
			Cell cell = createCell(workbook, sheet, excel_row, i, col.getHeader());
			cell.setCellStyle(headerStype);
			i++;
		}
	}

	private Cell createCell(XSSFWorkbook workbook, XSSFSheet sheet, Row row, int columnCount, Object value) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		cell.setCellStyle(generalStyle);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Timestamp) {
			cell.setCellStyle(dateStyle);
			cell.setCellValue((Timestamp) value);
		} else if (value instanceof BigDecimal) {
			cell.setCellStyle(decimalStyle);
			cell.setCellValue(((BigDecimal) value).doubleValue());
		} else if (value instanceof Double) {
			cell.setCellStyle(decimalStyle);
			cell.setCellValue((Double) value);
		} else {
			cell.setCellValue((String) value);
		}
		return cell;
	}

	private CellStyle getHeaderCellStyle(XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(14);
		style.setFont(font);
		return style;
	}

	private CellStyle getGeneralBodyCellStyle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		CellStyle style = workbook.createCellStyle();
		font.setFontHeight(12);
		style.setFont(font);
		return style;
	}

	private CellStyle getDateBodyCellStyle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat((short) 22); // 0x16, "m/d/yy h:mm"
		font.setFontHeight(12);
		style.setFont(font);
		return style;
	}

	private CellStyle getDecimalBodyCellStyle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(workbook.createDataFormat().getFormat("#,###.##"));
		font.setFontHeight(12);
		style.setFont(font);
		return style;
	}

}
