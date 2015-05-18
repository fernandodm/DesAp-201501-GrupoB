package ar.edu.unq.desapp.groupb.model.test;


import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Symptom;

public class SympthomTest extends TestCase {
	
	public void testSetNombre(){
		Symptom sintoma = new Symptom("Fernando");
		sintoma.setSymptomName("Esteban");
		
		assert(sintoma.getSymptomName() == "Esteban");
	}

}
