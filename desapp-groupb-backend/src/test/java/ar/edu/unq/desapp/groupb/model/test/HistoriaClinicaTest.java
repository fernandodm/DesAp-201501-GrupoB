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
import ar.edu.unq.desapp.groupb.model.Medicamento;
import ar.edu.unq.desapp.groupb.model.Persona;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class HistoriaClinicaTest extends TestCase {
	
	MedicalHistory historiaClinica;
	Persona persona;
	
	public void setUp(){
		persona = mock(Persona.class);
		historiaClinica = new MedicalHistory(60, 160, persona);
		
	}
	
	public void testAgregarAlergia(){
		
		historiaClinica.agregarAlergia("Penicilina");
		
		assert(historiaClinica.getAlergias().contains("Penicilina"));
	}
	
	public void testEsAlergicoATrue(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		alergias.add("Amoxicilina");
						
		assert(historiaClinica.isAllergicTo("Penicilina"));
	}
	
	public void testEsAlergicoAFalse(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
								
		assert(historiaClinica.isAllergicTo("Amoxicilina"));
	}
	
	public void testEliminarAlergia(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		historiaClinica.deleteAllergy("Penicilina");
		assert(historiaClinica.getAlergias().isEmpty());
	}
	
	public void testElPacienteEsCompatibleConTrue(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Treatment tratamiento = mock(Treatment.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento1.getDroga()).thenReturn("azitromicina");
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.patientSupports(tratamiento);
		
		assert(esCompatible);		
		
	}
	
	public void testElPacienteEsCompatibleConFalse(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Treatment tratamiento = mock(Treatment.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.patientSupports(tratamiento);
		
		assert(!esCompatible);		
	}
	
	public void testAgregarEvento(){

		Event evento = mock(Event.class);
		
		historiaClinica.addEvent(evento);
		
		boolean seAgrego = historiaClinica.getEventos().get(0).equals(evento);
		
		assertTrue(seAgrego);
		
	}
	
	public void testEventosDesdeFecha(){
		
		GregorianCalendar fecha1 = mock(GregorianCalendar.class);
		Diagnostic diagnostico2 = mock(Diagnostic.class);
		GregorianCalendar fecha2 = mock(GregorianCalendar.class);
		Diagnostic diagnostico3 = mock(Diagnostic.class);
		GregorianCalendar fecha3 = mock(GregorianCalendar.class);
		
		GregorianCalendar fecha4 = mock(GregorianCalendar.class);
		
		Event evento1 = mock(Event.class);
		Event evento2 = mock(Event.class);
		Event evento3 = mock(Event.class);
		
		when(evento1.getFecha()).thenReturn(fecha1);
		when(evento2.getFecha()).thenReturn(fecha2);
		when(evento3.getFecha()).thenReturn(fecha3);
		
		when(evento2.getDiagnostico()).thenReturn(diagnostico2);
		when(evento3.getDiagnostico()).thenReturn(diagnostico3);
		
		when(fecha1.after(fecha4)).thenReturn(false);
		when(fecha2.after(fecha4)).thenReturn(true);
		when(fecha3.after(fecha4)).thenReturn(true);
				
		historiaClinica.addEvent(evento1);
		historiaClinica.addEvent(evento2);
		historiaClinica.addEvent(evento3);
		
		List<Diagnostic> diagnosticosReturn = historiaClinica.eventsFromDate(fecha4);
		
		assertTrue(diagnosticosReturn.size() == 2);
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico2,diagnostico3)));
	}

}
