package ar.edu.unq.desapp.grupob;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.desapp.grupob.excepciones.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class ProfesionalTest extends TestCase {
	Profesional profesional;
	Sistema sistema;
	Paciente paciente;
	Paciente paciente2;
	Diagnostico diagnostico1;
	Diagnostico diagnostico2;
	Diagnostico diagnostico3;
	Diagnostico diagnostico4;
	HistoriaClinica historia1;
	HistoriaClinica historia2;
	List<HistoriaClinica> historias;
	GregorianCalendar fecha;
	
	public void setUp(){
		profesional = new Profesional("Fernando", "Tolaba", "35666897", "fer11", "1234");
		sistema = mock(Sistema.class);
		paciente = mock(Paciente.class);
		paciente2 = mock(Paciente.class);
		historia1 = mock(HistoriaClinica.class);
		historia2 = mock(HistoriaClinica.class);
		diagnostico1 = mock(Diagnostico.class);
		diagnostico2 = mock(Diagnostico.class);
		diagnostico3 = mock(Diagnostico.class);
		diagnostico4 = mock(Diagnostico.class);
		fecha = mock(GregorianCalendar.class);
		historias = Arrays.asList(historia1, historia2);
	}
	
	
	public void testObtenerHistoriaClinicaDeConPaciente() throws PacienteNoEncontradoException{
				
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
				
		HistoriaClinica historia = profesional.obtenerHistoriaClinicaDe(paciente, sistema);
		
		assert(historia == historia2);
	}
	
	public void testObtenerHistoriaClinicaDeSinPaciente(){
		
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
	
	public void testTratamientosParaElDiagnosticoConDiagnostico() throws DiagnosticoNoEncontradoException{
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha, diagnostico1);
		eventos1.put(fecha, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos2.put(fecha, diagnostico3);
		eventos2.put(fecha, diagnostico4);
		eventos2.put(fecha, diagnostico2);
		
		Tratamiento tratamiento1 = mock(Tratamiento.class);
		Tratamiento tratamiento2 = mock(Tratamiento.class);
		Tratamiento tratamiento3 = mock(Tratamiento.class);
		Tratamiento tratamiento4 = mock(Tratamiento.class);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getEventos()).thenReturn(eventos1);
		when(historia2.getEventos()).thenReturn(eventos2);
		when(diagnostico1.getTratamiento()).thenReturn(tratamiento1);
		when(diagnostico2.getTratamiento()).thenReturn(tratamiento2);
		when(diagnostico3.getTratamiento()).thenReturn(tratamiento3);
		when(diagnostico4.getTratamiento()).thenReturn(tratamiento4);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Gripe");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		List<Tratamiento> tratamientos = profesional.tratamientosParaElDiagnostico("Sinositis", sistema);
		
		List<Tratamiento> tratmientoAComparar = Arrays.asList(tratamiento1, tratamiento4);
		
		assert(tratamientos.size() == 2);
		assert(tratamientos.containsAll(tratmientoAComparar));
	
	}
	
public void testTratamientosParaElDiagnosticoSinnDiagnostico() {
		
	List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
	
	HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
	eventos1.put(fecha, diagnostico1);
	eventos1.put(fecha, diagnostico2);
	
	HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
	eventos2.put(fecha, diagnostico3);
	eventos2.put(fecha, diagnostico4);
	eventos2.put(fecha, diagnostico2);
	
	Tratamiento tratamiento1 = mock(Tratamiento.class);
	Tratamiento tratamiento2 = mock(Tratamiento.class);
	Tratamiento tratamiento3 = mock(Tratamiento.class);
	Tratamiento tratamiento4 = mock(Tratamiento.class);
	
	when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
	when(sistema.getHistorias()).thenReturn(historias);
	when(historia1.getEventos()).thenReturn(eventos1);
	when(historia2.getEventos()).thenReturn(eventos2);
	when(diagnostico1.getTratamiento()).thenReturn(tratamiento1);
	when(diagnostico2.getTratamiento()).thenReturn(tratamiento2);
	when(diagnostico3.getTratamiento()).thenReturn(tratamiento3);
	when(diagnostico4.getTratamiento()).thenReturn(tratamiento4);
	when(diagnostico1.getNombre()).thenReturn("SinoSitis");
	when(diagnostico2.getNombre()).thenReturn("Resfriado");
	when(diagnostico3.getNombre()).thenReturn("Sinositis");
	when(diagnostico4.getNombre()).thenReturn("sinositis");
		
	
		try {
			profesional.tratamientosParaElDiagnostico("Gripe porcina", sistema);
			fail("Se produjo un error en: testTratamientosParaElDiagnosticoSinnDiagnostico()");
		} catch (DiagnosticoNoEncontradoException e) {
			
		}
	}

	public void testTieneDiagnosticoTrue(){
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Sinositis");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = profesional.tieneDiagnostico("RESFRIADO", sistema);
		
		assert(tieneElDiagnostico);
	}
	
	public void testTieneDiagnosticoFalse(){
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Sinositis");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = profesional.tieneDiagnostico("Gripe", sistema);
		
		assertFalse(tieneElDiagnostico);
	}

	
	public void testSugerirTratamientosParaElPaciente()throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha, diagnostico1);
		eventos1.put(fecha, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos2.put(fecha, diagnostico3);
		eventos2.put(fecha, diagnostico4);
		eventos2.put(fecha, diagnostico2);
		
		Tratamiento tratamiento1 = mock(Tratamiento.class);
		Tratamiento tratamiento2 = mock(Tratamiento.class);
		Tratamiento tratamiento3 = mock(Tratamiento.class);
		Tratamiento tratamiento4 = mock(Tratamiento.class);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(sistema.getHistorias()).thenReturn(historias);
		
		when(historia1.getEventos()).thenReturn(eventos1);
		when(historia2.getEventos()).thenReturn(eventos2);
		when(diagnostico1.getTratamiento()).thenReturn(tratamiento1);
		when(diagnostico2.getTratamiento()).thenReturn(tratamiento2);
		when(diagnostico3.getTratamiento()).thenReturn(tratamiento3);
		when(diagnostico4.getTratamiento()).thenReturn(tratamiento4);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("sinositis");
		when(diagnostico3.getNombre()).thenReturn("Gripe");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
		
		when(historia2.elPacienteEsCompatibleCon(tratamiento1)).thenReturn(true);
		when(historia2.elPacienteEsCompatibleCon(tratamiento4)).thenReturn(true);
		when(historia2.elPacienteEsCompatibleCon(tratamiento2)).thenReturn(false);
		
	
		
		
		List<Tratamiento> tratamientos = profesional.sugerirTratamientosParaElPaciente(paciente,"Sinositis", sistema);
		
		List<Tratamiento> tratmientoAComparar = Arrays.asList(tratamiento1,tratamiento4);
		
		assert(tratamientos.size() == 2);
		assert(tratamientos.containsAll(tratmientoAComparar));	

	}
	
	public void testDiagnosticosPosiblesParaSintomatologia(){
	
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		Sintoma sintoma1 = mock(Sintoma.class);
		Sintoma sintoma2 = mock(Sintoma.class);
		Sintoma sintoma3 = mock(Sintoma.class);
		Sintoma sintoma4 = mock(Sintoma.class);
		Sintoma sintoma5 = mock(Sintoma.class);
		
		List<Sintoma> sintomas1 = Arrays.asList(sintoma1,sintoma2);
		List<Sintoma> sintomas2 = Arrays.asList(sintoma5,sintoma4);
		List<Sintoma> sintomas3 = Arrays.asList(sintoma3,sintoma2,sintoma5);
		
		when(diagnostico1.getSintomas()).thenReturn(sintomas1);
		when(diagnostico2.getSintomas()).thenReturn(sintomas2);
		when(diagnostico3.getSintomas()).thenReturn(sintomas1);
		when(diagnostico4.getSintomas()).thenReturn(sintomas3);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
		
		List<Diagnostico> diagnosticosReturn = profesional.diagnosticosPosiblesParaSintomatologia(sintomas1, sistema);
		
		assert(diagnosticosReturn.size() == 2);
		assert(diagnosticosReturn.containsAll(Arrays.asList(diagnostico1,diagnostico3)));
	}
}
