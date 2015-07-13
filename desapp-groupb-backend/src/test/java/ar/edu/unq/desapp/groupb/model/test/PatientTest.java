package ar.edu.unq.desapp.groupb.model.test;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Patient;

public class PatientTest extends TestCase {

	Patient patient;
	
	public void setUp(){
		patient = new Patient("Esteban","Di Meglia","36811371");
	}
		
	public void testAddAllergy(){
		MedicalHistory history = mock(MedicalHistory.class);
		
		patient.setMedicalHistory(history);
		patient.addAllergy("Ibuprofeno");
		
		verify(history).addAllergy("Ibuprofeno");
	}
	
	public void testDeleteAllergy(){
		MedicalHistory history = mock(MedicalHistory.class);
		
		patient.setMedicalHistory(history);
		patient.deleteAllergy("Ibuprofeno");
		
		verify(history).deleteAllergy("Ibuprofeno");
	}
}
