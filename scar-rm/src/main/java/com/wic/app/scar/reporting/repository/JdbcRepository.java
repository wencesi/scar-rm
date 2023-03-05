package com.wic.app.scar.reporting.repository;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wic.app.scar.reporting.model.Consulta;
import com.wic.app.scar.reporting.model.Parametro;

@Repository
public class JdbcRepository {

	Logger logger = LoggerFactory.getLogger(JdbcRepository.class);		
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> executeQry(Consulta consulta) {
		String sql = consulta.getSql();
		for(Parametro param: consulta.getParametros()) {
			logger.info("reemplazo " + param.getCodigo() + " por " + param.getValor());
			sql = sql.replace(param.getCodigo(), param.getValor());
		}
		logger.info("SQL: "+sql);
		return jdbcTemplate.queryForList(sql);
	}	
	
}
