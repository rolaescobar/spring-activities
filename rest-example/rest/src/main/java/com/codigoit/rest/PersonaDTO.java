package com.codigoit.rest;

public class PersonaDTO {
	private String nombre;
	private String apellido;
	private int edad;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
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
	public PersonaDTO(String nombre, String apellido, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
	public PersonaDTO() {
		super();
	}
	
	public PersonaDTO(Persona persona) {
		this.setNombre(persona.getNombre());
		this.setApellido(persona.getApellido());;
		this.setEdad(persona.getEdad());
	}
	
	
}
