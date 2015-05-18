package ar.edu.unq.desapp.groupb.model.test;


import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Person;

public class PersonTest extends TestCase {
	
	Person person;

	public void setUp(){
		person = new Person("", "", "", "", "");
	}

	public void testSetNombre(){
		person.setFirstname("Fernando");
		
		assert(person.getFirstname() == "Fernando");
	}
	
	public void testSetApellido(){
		person.setLastname("Di Meglio");
		
		assert(person.getLastname() == "Di Meglio");
	}
	
	public void testSetDni(){
		person.setDni("32555666");
		
		assert(person.getDni() == "32555666");
	}
	
	public void testSetUsuario(){
		person.setUsername("fer_10");
		
		assert(person.getUsername() == "fer_10");
	}
	
	public void testSetContrasena(){
		person.setPassword("1234");
		
		assert(person.getPassword() == "1234");
	}
}
