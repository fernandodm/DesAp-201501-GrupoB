package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
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
//		medicalHistory = new MedicalHistory(60, 160, patient);
		
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
	
//	public void testEventosDesdeFecha(){
//		
//		GregorianCalendar fecha1 = mock(GregorianCalendar.class);
//		Diagnostic diagnostico2 = mock(Diagnostic.class);
//		GregorianCalendar fecha2 = mock(GregorianCalendar.class);
//		Diagnostic diagnostico3 = mock(Diagnostic.class);
//		GregorianCalendar fecha3 = mock(GregorianCalendar.class);
//		
//		GregorianCalendar fecha4 = mock(GregorianCalendar.class);
//		
//		Event evento1 = mock(Event.class);
//		Event evento2 = mock(Event.class);
//		Event evento3 = mock(Event.class);
//		
//		when(evento1.getFecha()).thenReturn(fecha1);
//		when(evento2.getFecha()).thenReturn(fecha2);
//		when(evento3.getFecha()).thenReturn(fecha3);
//		
//		when(evento2.getDiagnostic()).thenReturn(diagnostico2);
//		when(evento3.getDiagnostic()).thenReturn(diagnostico3);
//		
//		when(fecha1.after(fecha4)).thenReturn(false);
//		when(fecha2.after(fecha4)).thenReturn(true);
//		when(fecha3.after(fecha4)).thenReturn(true);
//				
//		medicalHistory.addEvent(evento1);
//		medicalHistory.addEvent(evento2);
//		medicalHistory.addEvent(evento3);
//		
//		List<Diagnostic> diagnosticosReturn = medicalHistory.eventsFromDate(fecha4);
//		
//		assertTrue(diagnosticosReturn.size() == 2);
//		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico2,diagnostico3)));
//	}

}
