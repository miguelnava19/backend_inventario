package com.api.kruger.response;

import java.io.Serializable;
import java.util.List;

public class ModelResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mensaje;
	private String estatus;
	private Integer status;
	private Object entidad;
	private String error;
	private List<String> errors;
	private ModelVersion version;

	public ModelVersion getVersion() {
		return version;
	}

	public void setVersion(ModelVersion version) {
		this.version = version;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getEntidad() {
		return entidad;
	}

	public void setEntidad(Object entidad) {
		this.entidad = entidad;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
