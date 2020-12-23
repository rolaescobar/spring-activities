package com.codigoit.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/personas")
public class PersonaRestService {

	private static List<Persona> lista = new ArrayList<Persona>();
	
	static {
		Persona p1 = new Persona("Rolando","García",32);
		Persona p2 = new Persona("Ana","Gomez",25);
		lista.add(p1);
		lista.add(p2);
	}
	
	@GetMapping
	public List<PersonaDTO>buscarTodos(){
		
		try {
			return lista.stream().map(PersonaDTO::new).collect(Collectors.toList());
	        
	    } catch(NumberFormatException e) {
	    	throw new RuntimeException ("El servicio a fallado",new Exception("no hay conexión con la base de datos"));
	    } 
		
	}
	

	@GetMapping("/{nombre}")

	public PersonaDTO buscarUno(@PathVariable String nombre) {
	    
	
		return  lista.stream().filter(p->p.getNombre().equals(nombre)).findFirst().map(PersonaDTO::new ).orElse(null);
	}
	

	@DeleteMapping(value="/{nombre}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void borrar(@PathVariable String nombre) {
		
		lista.removeIf(p->p.getNombre().equals(nombre));
	}
	
	
	
	@PostMapping
	public ResponseEntity<PersonaDTO> insertar(@RequestBody PersonaDTO personaDto,UriComponentsBuilder builder) {
		
		Persona persona = new Persona(personaDto.getNombre(),personaDto.getApellido(),personaDto.getEdad());
	    lista.add(persona);
	    return ResponseEntity.status(HttpStatus.CREATED).body(personaDto);
	}
	
	
	@PutMapping(value="/{nombre}")
	
	public ResponseEntity<Void> actualizar(@PathVariable String nombre,@RequestBody PersonaDTO personaDto) {
		
		int posicion = lista.indexOf(new Persona(nombre));
		Persona persona = new Persona(personaDto.getNombre(),personaDto.getApellido(),personaDto.getEdad());
		if(posicion !=-1) {
	    	
	    	lista.set(posicion, persona);
	    	return ResponseEntity.status(HttpStatus.OK).build();
	    }else {
	    	lista.add(persona);
	    	return ResponseEntity.status(HttpStatus.CREATED).build();
	    }
	}
	
	@PatchMapping(value="/{nombre}")
	
	public ResponseEntity<Void> actualizarParcial(@PathVariable String nombre,@RequestBody PersonaPartialDTO personaPartialDto) {
		
		int posicion = lista.indexOf(new Persona(nombre));
        Persona persona = lista.get(posicion);
        persona.setApellido(personaPartialDto.getApellido());
        persona.setEdad(personaPartialDto.getEdad());
		if(posicion !=-1) {
	    	
	    	lista.set(posicion, persona);
	    	return ResponseEntity.status(HttpStatus.OK).build();
	    }else {
	    	
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
}
