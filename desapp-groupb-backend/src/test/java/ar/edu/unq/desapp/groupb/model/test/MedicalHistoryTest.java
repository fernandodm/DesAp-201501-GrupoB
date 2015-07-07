package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Medicine;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class MedicalHistoryTest extends TestCase {
	
	MedicalHistory medicalHistory;
	Patient patient;
	Diagnostic diagnostic1;
	Diagnostic diagnostic2;

	
	public void setUp(){
		medicalHistory = new MedicalHistory();
		patient = mock(Patient.class);
	
	}
	
	public void testAgregarAlergia(){
	
		medicalHistory.addAllergy("Penicilina");
		
		assert(medicalHistory.getAllergies().contains("Penicilina"));
	}
	
//	public void testGetDiagnostics(){
//		
//		diagnostic1 = mock(Diagnostic.class);
//		diagnostic2 = mock(Diagnostic.class);
//		
//		event1 = mock(Event.class);
//		event2 = mock(Event.class);
//		
//		when(event1.getDiagnostic()).thenReturn(diagnostic1);
//		when(event2.getDiagnostic()).thenReturn(diagnostic2);
//		
//		List<Event> events = Arrays.asList(event1, event2);
//				
//		medicalHistory.setEvents(events);
//		
//		List<Diagnostic> diagnosesReturn = medicalHistory.getDiagnostics();
//		List<Diagnostic> diagnoses = Arrays.asList(diagnostic1,diagnostic2);
//		
//		assert(diagnosesReturn.containsAll(diagnoses));
//	}
	
	public void testEsAlergicoATrue(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		alergias.add("Amoxicilina");
		medicalHistory.setAllergies(alergias);	
		assert(medicalHistory.isAllergicTo("Penicilina"));
	}
	
	public void testEsAlergicoAFalse(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		medicalHistory.setAllergies(alergias);			
		assertFalse(medicalHistory.isAllergicTo("Amoxicilina"));
	}
	
	public void testEliminarAlergia(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		medicalHistory.deleteAllergy("Penicilina");
		assert(medicalHistory.getAllergies().isEmpty());
	}
	
	public void testElPacienteEsCompatibleConTrue(){
		Medicine medicamento1 = mock(Medicine.class);
		Medicine medicamento2 = mock(Medicine.class);
		Treatment tratamiento = mock(Treatment.class);
		
		List<Medicine> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicines()).thenReturn(medicamentos);
		when(medicamento1.getDrugName()).thenReturn("azitromicina");
		when(medicamento2.getDrugName()).thenReturn("amoxicilina");
		
		medicalHistory.addAllergy("azitromicina");
		medicalHistory.addAllergy("ibuprofeno");
		medicalHistory.addAllergy("clindamicina");
		
		boolean esCompatible = medicalHistory.patientSupports(tratamiento);
		
		assert(esCompatible);		
		
	}
	
	public void testElPacienteEsCompatibleConFalse(){
		Medicine medicamento1 = mock(Medicine.class);
		Medicine medicamento2 = mock(Medicine.class);
		Treatment tratamiento = mock(Treatment.class);
		
		List<Medicine> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicines()).thenReturn(medicamentos);
		when(medicamento2.getDrugName()).thenReturn("amoxicilina");
		
		medicalHistory.addAllergy("azitromicina");
		medicalHistory.addAllergy("ibuprofeno");
		medicalHistory.addAllergy("clindamicina");
		
		boolean esCompatible = medicalHistory.patientSupports(tratamiento);
		
		assert(!esCompatible);		
	}
}
