package ar.edu.unq.desapp.grupob;

import junit.framework.TestCase;

public class SintomaTest extends TestCase {
	
	public void testSetNombre(){
		Sintoma sintoma = new Sintoma("Fernando");
		sintoma.setNombre("Esteban");
		
		assert(sintoma.getNombre() == "Esteban");
	}

}
