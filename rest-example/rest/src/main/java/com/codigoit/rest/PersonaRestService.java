package com.codigoit.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class PersonaRestService {

	private static List<Persona> lista = new ArrayList<Persona>();
	
	static {
		Persona p1 = new Persona("Rolando","García",32);
		Persona p2 = new Persona("Ana","Gomez",25);
		lista.add(p1);
		lista.add(p2);
	}
	
	@RequestMapping("/personas")
	@ResponseBody
	public List<Persona>buscarTodos(){
		return lista;
	}
	

	@RequestMapping("/personas/{nombre}")
	@ResponseBody
	public Persona buscarUno(@PathVariable String nombre) {
	    
	
		return  lista.stream().filter(p->p.getNombre().equals(nombre)).findFirst().orElse(null);
	}
	

	@RequestMapping(value="/personas/{nombre}",method=RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void borrar(@PathVariable String nombre) {
		
		lista.removeIf(p->p.getNombre().equals(nombre));
	}
	
	
	
	@RequestMapping(value="/personas",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> insertar(@RequestBody Persona persona,UriComponentsBuilder builder) {
		
		lista.add(persona);
		//return ResponseEntity.status(HttpStatus.CREATED).body(persona);
		
		HttpHeaders cabecera= new HttpHeaders();		
		UriComponents miUrl = builder.path("/personas/{nombre}").buildAndExpand(persona.getNombre());
		cabecera.setLocation(miUrl.toUri());
		
		 return new ResponseEntity<Void>(cabecera,HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/personas/{nombre}",method=RequestMethod.PUT)
	@ResponseBody
	public void actualizar(@PathVariable String nombre,@RequestBody Persona persona) {
		
		int posicion = lista.indexOf(new Persona(nombre));
		lista.set(posicion, persona);
	}
	
}
