package com.wic.app.scar.reporting.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planificador")
public class Planificador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha_peticion")
	private LocalDateTime fechaPeticion;

	@Column(name = "fecha_ejecucion")
	private LocalDateTime fechaEjecucion;	
	
	@Column(name = "tipo")
	private String tipo;

	@Column(name = "modo")
	private String modo;	
	
	@Column(name = "codigo")
	private String codigo;	
	
	@Column(name = "usuario")
	private String usuario;		

	@Column(name = "estado")
	private String estado;	

	@Column(name = "fichero")
	private String fichero;	
	
	public Planificador() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaPeticion() {
		return fechaPeticion;
	}
	
	public void setFechaPeticion(LocalDateTime fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}
	
	public LocalDateTime getFechaEjecucion() {
		return fechaEjecucion;
	}
	
	public void setFechaEjecucion(LocalDateTime fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModo() {
		return modo;
	}
	
	public void setModo(String modo) {
		this.modo = modo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Planificador (id/codigo/fichero/tipo): " 
				+ this.getId() + "," 
				+ this.getCodigo() + ","
				+ this.getFichero() + "," 
				+ this.getTipo();
	}
}
