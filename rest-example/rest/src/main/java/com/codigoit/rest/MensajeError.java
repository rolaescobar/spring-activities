package com.codigoit.rest;

public class MensajeError {

	private String mensaje;
	private String causa;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public MensajeError(String mensaje, String causa) {
		super();
		this.mensaje = mensaje;
		this.causa = causa;
	}
	
}
