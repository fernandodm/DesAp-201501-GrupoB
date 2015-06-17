package ar.edu.unq.desapp.groupb.model.test;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Patient;

public class PatientTest extends TestCase {

	Patient patient;
	
	public void setUp(){
		patient = new Patient("Esteban","Di Meglia","36811371","edm","amdamd");
	}
		
}
