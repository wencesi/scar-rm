package com.wic.app.scar.reporting.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Columna> columnas;	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parametro> parametros;		
	
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;

	
	@NotBlank(message = "Email is mandatory")	
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;	
	
	@Column(name = "orden")
	private Integer orden;
	
	@Column(name = "sql")
	private String sql;	

	public Consulta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setColumnas(List<Columna> columnas) {
		this.columnas = columnas;
	}
	
	public List<Columna> getColumnas() {
		return columnas;
	}
	
	public List<Parametro> getParametros() {
		return parametros;
	}
	
	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public Integer getOrden() {
		return orden;
	}
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Override
	public String toString() {
		return "Consulta (id/codigo/nombre/orden/sql/descripcion): " 
				+ this.getId() + "," 
				+ this.getCodigo() + "," 
				+ this.getNombre() + "," 
				+ this.getOrden() + "," 
				+ this.getSql() + "," 								
				+ this.getDescripcion();
	}
}
