package ar.edu.unq.desapp.groupb.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Person;
import ar.edu.unq.desapp.groupb.model.System;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;



public class PatientTest extends TestCase {

	Patient patient;
	
	public void setUp(){
		patient = new Patient("Esteban","Di Meglia","36811371","edm","amdamd");
	}
	
	public void testRegistrarEnElSistema() throws NombreDeUsuarioYaTomado{
		
		
		System system = mock(System.class);
		ArrayList<Person> users =  new ArrayList<Person>();
		
		when(system.getUsers()).thenReturn(users);
		
//		patient.registrarEnElSistema(system, patient.getFirstname(), patient.getLastname(), patient.getDni(), patient.getUsername(), patient.getPassword(), 85, 180);
//		
//		verify(system).registerNewPatientUser(patient.getFirstname(), patient.getLastname(), patient.getDni(), patient.getUsername(), patient.getPassword(), 85, 180);
	}
	
	
}
