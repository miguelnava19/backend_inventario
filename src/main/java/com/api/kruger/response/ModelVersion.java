package com.api.kruger.response;

import com.api.kruger.util.Constantes;

import java.io.Serializable;


public class ModelVersion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String version = Constantes.VERSION_APP;
	private String fecha = Constantes.FECHA_VERSION;
	private String descripcion = Constantes.DESCRIPCION;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
