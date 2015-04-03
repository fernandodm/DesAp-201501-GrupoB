package ar.edu.unq.desapp.grupob;

import junit.framework.TestCase;

public class PersonaTest extends TestCase {
	
	Persona persona;

	public void setUp(){
		persona = new Persona("", "", "", "", "");
	}

	public void testSetNombre(){
		persona.setNombre("Fernando");
		
		assert(persona.getNombre() == "Fernando");
	}
	
	public void testSetApellido(){
		persona.setApellido("Di Meglio");
		
		assert(persona.getApellido() == "Di Meglio");
	}
	
	public void testSetDni(){
		persona.setDni("32555666");
		
		assert(persona.getDni() == "32555666");
	}
	
	public void testSetUsuario(){
		persona.setUsuario("fer_10");
		
		assert(persona.getUsuario() == "fer_10");
	}
	
	public void testSetContrasena(){
		persona.setContrasena("1234");
		
		assert(persona.getContrasena() == "1234");
	}
}
