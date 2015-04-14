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
		
		Diagnostico diagnostico = mock(Diagnostico.class);
		GregorianCalendar fecha = mock(GregorianCalendar.class);
		
		historiaClinica.agregarEvento(fecha, diagnostico);
		
		boolean seAgrego = historiaClinica.getEventos().get(fecha).equals(diagnostico);
		
		assertTrue(seAgrego);
		
	}
	
	public void testEventosDesdeFecha(){
		
		Diagnostico diagnostico1 = mock(Diagnostico.class);
		GregorianCalendar fecha1 = mock(GregorianCalendar.class);
		Diagnostico diagnostico2 = mock(Diagnostico.class);
		GregorianCalendar fecha2 = mock(GregorianCalendar.class);
		Diagnostico diagnostico3 = mock(Diagnostico.class);
		GregorianCalendar fecha3 = mock(GregorianCalendar.class);
		
		GregorianCalendar fecha4 = mock(GregorianCalendar.class);
		
		when(fecha1.after(fecha4)).thenReturn(false);
		when(fecha2.after(fecha4)).thenReturn(true);
		when(fecha3.after(fecha4)).thenReturn(true);
		
		historiaClinica.agregarEvento(fecha1, diagnostico1);
		historiaClinica.agregarEvento(fecha2, diagnostico2);
		historiaClinica.agregarEvento(fecha3, diagnostico3);
		
		boolean retornaLasCorrectas = historiaClinica.eventosDesdeFecha(fecha4).size() == 2;
		
		assertTrue(retornaLasCorrectas);
	}

}
