package com.codigoit.rest;

public class PersonaPartialDTO {

	private String apellido;
	private int edad;
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public PersonaPartialDTO(String apellido, int edad) {
		super();
		this.apellido = apellido;
		this.edad = edad;
	}
	public PersonaPartialDTO() {
		super();
	}
	
}
