package ar.edu.unq.desapp.grupob;

import java.util.Arrays;
import java.util.List;

import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class ProfesionalTest extends TestCase {
	Profesional profesional;
	Sistema sistema;
	Paciente paciente;
	Paciente paciente2;
	
	public void setUp(){
		profesional = new Profesional("Fernando", "Tolaba", "35666897", "fer11", "1234");
		sistema = mock(Sistema.class);
		paciente = mock(Paciente.class);
		paciente2 = mock(Paciente.class);
	}
	
	
	public void testObtenerHistoriaClinicaDeConPaciente() throws PacienteNoEncontradoException{
		
		HistoriaClinica historia1 = mock(HistoriaClinica.class);
		HistoriaClinica historia2 = mock(HistoriaClinica.class);
		
		List<HistoriaClinica> historias = Arrays.asList(historia1, historia2);
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
				
		HistoriaClinica historia = profesional.obtenerHistoriaClinicaDe(paciente, sistema);
		
		assert(historia == historia2);
	}
	
	public void testObtenerHistoriaClinicaDeSinPaciente(){
		
		HistoriaClinica historia1 = mock(HistoriaClinica.class);
		HistoriaClinica historia2 = mock(HistoriaClinica.class);
		
		List<HistoriaClinica> historias = Arrays.asList(historia1, historia2);
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
		
		Paciente paciente3 = mock(Paciente.class);
				
		try {
			profesional.obtenerHistoriaClinicaDe(paciente3, sistema);
			fail("Se produjo un error en: testObtenerHistoriaClinicaDeSinPaciente()");
		} catch (PacienteNoEncontradoException e) {
			
		}
		
	}

}
