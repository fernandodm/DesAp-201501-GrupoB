package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Medicine;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class MedicalHistoryTest extends TestCase {
	
	MedicalHistory medicalHistory;
	Patient patient;
	
	public void setUp(){
		patient = mock(Patient.class);		
	}
	
	public void testAgregarAlergia(){
	
		medicalHistory.addAllergy("Penicilina");
		
		assert(medicalHistory.getAllergies().contains("Penicilina"));
	}
	
	public void testEsAlergicoATrue(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		alergias.add("Amoxicilina");
						
		assert(medicalHistory.isAllergicTo("Penicilina"));
	}
	
	public void testEsAlergicoAFalse(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
								
		assert(medicalHistory.isAllergicTo("Amoxicilina"));
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
	
	public void testAgregarEvento(){

		Event evento = mock(Event.class);
		
		medicalHistory.addEvent(evento);
		
		boolean seAgrego = medicalHistory.getEvents().get(0).equals(evento);
		
		assertTrue(seAgrego);
		
	}
}
