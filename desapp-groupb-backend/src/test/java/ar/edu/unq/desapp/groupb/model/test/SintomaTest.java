package ar.edu.unq.desapp.groupb.model.test;


import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Symptom;

public class SintomaTest extends TestCase {
	
	public void testSetNombre(){
		Symptom sintoma = new Symptom("Fernando");
		sintoma.setNombre("Esteban");
		
		assert(sintoma.getNombre() == "Esteban");
	}

}
