package ar.edu.unq.desapp.grupob;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

public class HistoriaClinicaTest extends TestCase {
	
	HistoriaClinica historiaClinica;
	Persona persona;
	
	public void setUp(){
		persona = mock(Persona.class);
		historiaClinica = new HistoriaClinica(60, 160, persona);
		
	}
	
	public void testAgregarAlergia(){
		
		historiaClinica.agregarAlergia("Penicilina");
		
		assert(historiaClinica.getAlergias().contains("Penicilina"));
	}
	
	public void testEsAlergicoATrue(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		alergias.add("Amoxicilina");
						
		assert(historiaClinica.esAlergicoA("Penicilina"));
	}
	
	public void testEsAlergicoAFalse(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
								
		assert(historiaClinica.esAlergicoA("Amoxicilina"));
	}
	
	public void testEliminarAlergia(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		historiaClinica.eliminarAlergia("Penicilina");
		assert(historiaClinica.getAlergias().isEmpty());
	}
	
	public void testElPacienteEsCompatibleConTrue(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Tratamiento tratamiento = mock(Tratamiento.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento1.getDroga()).thenReturn("azitromicina");
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.elPacienteEsCompatibleCon(tratamiento);
		
		assert(esCompatible);		
		
	}
	
	public void testElPacienteEsCompatibleConFalse(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Tratamiento tratamiento = mock(Tratamiento.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.elPacienteEsCompatibleCon(tratamiento);
		
		assert(!esCompatible);		
	}
	
	public void testAgregarEvento(){

		Evento evento = mock(Evento.class);
		
		historiaClinica.agregarEvento(evento);
		
		boolean seAgrego = historiaClinica.getEventos().get(0).equals(evento);
		
		assertTrue(seAgrego);
		
	}
	
	public void testEventosDesdeFecha(){
		
		GregorianCalendar fecha1 = mock(GregorianCalendar.class);
		Diagnostico diagnostico2 = mock(Diagnostico.class);
		GregorianCalendar fecha2 = mock(GregorianCalendar.class);
		Diagnostico diagnostico3 = mock(Diagnostico.class);
		GregorianCalendar fecha3 = mock(GregorianCalendar.class);
		
		GregorianCalendar fecha4 = mock(GregorianCalendar.class);
		
		Evento evento1 = mock(Evento.class);
		Evento evento2 = mock(Evento.class);
		Evento evento3 = mock(Evento.class);
		
		when(evento1.getFecha()).thenReturn(fecha1);
		when(evento2.getFecha()).thenReturn(fecha2);
		when(evento3.getFecha()).thenReturn(fecha3);
		
		when(evento2.getDiagnostico()).thenReturn(diagnostico2);
		when(evento3.getDiagnostico()).thenReturn(diagnostico3);
		
		when(fecha1.after(fecha4)).thenReturn(false);
		when(fecha2.after(fecha4)).thenReturn(true);
		when(fecha3.after(fecha4)).thenReturn(true);
				
		historiaClinica.agregarEvento(evento1);
		historiaClinica.agregarEvento(evento2);
		historiaClinica.agregarEvento(evento3);
		
		List<Diagnostico> diagnosticosReturn = historiaClinica.eventosDesdeFecha(fecha4);
		
		assertTrue(diagnosticosReturn.size() == 2);
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico2,diagnostico3)));
	}

}
