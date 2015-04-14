package ar.edu.unq.desapp.grupob;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.desapp.grupob.excepciones.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;
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
		historias = Arrays.asList(historia1, historia2);
	}
	
	
	public void testObtenerHistoriaClinicaDeConPaciente() throws PacienteNoEncontradoException{
				
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
				
		HistoriaClinica historia = profesional.obtenerHistoriaClinicaDe(paciente, sistema);
		
		assertTrue(historia == historia2);
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
		
		GregorianCalendar fecha1 = new GregorianCalendar(1,2,3);
		GregorianCalendar fecha2 = new GregorianCalendar(4,5,6);
		GregorianCalendar fecha3 = new GregorianCalendar(7,8,9);
		GregorianCalendar fecha4 = new GregorianCalendar(9,9,9);
		GregorianCalendar fecha5 = new GregorianCalendar(3,3,3);
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha1, diagnostico1);
		eventos1.put(fecha2, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos2.put(fecha3, diagnostico3);
		eventos2.put(fecha4, diagnostico4);
		eventos2.put(fecha5, diagnostico2);
		
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
		when(diagnostico1.getNombre()).thenReturn("Sinositis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Gripe");
		when(diagnostico4.getNombre()).thenReturn("Sinositis");
				
		List<Tratamiento> tratamientos = profesional.tratamientosParaElDiagnostico("Sinositis", sistema);
		
		List<Tratamiento> tratmientoAComparar = Arrays.asList(tratamiento1, tratamiento4);
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));
	
	}
	
	public void testTratamientosParaElDiagnosticoSinDiagnostico() {
	
		GregorianCalendar fecha1 = new GregorianCalendar(1,2,3);
		GregorianCalendar fecha2 = new GregorianCalendar(4,5,6);
		GregorianCalendar fecha3 = new GregorianCalendar(7,8,9);
		GregorianCalendar fecha4 = new GregorianCalendar(9,9,9);
		GregorianCalendar fecha5 = new GregorianCalendar(3,3,3);
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha1, diagnostico1);
		eventos1.put(fecha2, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos2.put(fecha3, diagnostico3);
		eventos2.put(fecha4, diagnostico4);
		eventos2.put(fecha5, diagnostico2);
		
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
		
		assertTrue(tieneElDiagnostico);
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
		
		GregorianCalendar fecha1 = new GregorianCalendar(1,2,3);
		GregorianCalendar fecha2 = new GregorianCalendar(4,5,6);
		GregorianCalendar fecha3 = new GregorianCalendar(7,8,9);
		GregorianCalendar fecha4 = new GregorianCalendar(9,9,9);
		GregorianCalendar fecha5 = new GregorianCalendar(3,3,3);
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha1, diagnostico1);
		eventos1.put(fecha2, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos2.put(fecha3, diagnostico3);
		eventos2.put(fecha4, diagnostico4);
		eventos2.put(fecha5, diagnostico2);
		
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
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));	

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
		
		assertTrue(diagnosticosReturn.size() == 2);
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico1,diagnostico3)));
	}
	
	public void testQuienesTuvieronUnSintomaTambienTuvieron(){
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
	
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
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
		
		List<Diagnostico> diagnosticosReturn = profesional.quienesTuvieronUnSintomaTambienTuvieron(sintoma2, sistema);
		
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico4, diagnostico3, diagnostico1)));
	}
	
	public void testQuienesTuvieronUnaEnfermedadTambienTuvieron(){
		
		GregorianCalendar fecha1 = new GregorianCalendar(1,2,3);
		GregorianCalendar fecha2 = new GregorianCalendar(4,5,6);
		GregorianCalendar fecha3 = new GregorianCalendar(7,8,9);
		GregorianCalendar fecha4 = new GregorianCalendar(9,9,9);
		GregorianCalendar fecha5 = new GregorianCalendar(3,3,3);
		GregorianCalendar fecha6 = new GregorianCalendar(3,3,8);
		
		HistoriaClinica historia3 = mock(HistoriaClinica.class);
		Diagnostico diagnostico5 = mock(Diagnostico.class);
		
		List<HistoriaClinica> historias = Arrays.asList(historia1, historia2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha1, diagnostico1);
		eventos1.put(fecha2, diagnostico2);
		
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha3, diagnostico3);
		eventos1.put(fecha4, diagnostico4);
		eventos1.put(fecha5, diagnostico1);
		
		HashMap<GregorianCalendar,Diagnostico> eventos3 = new HashMap<GregorianCalendar,Diagnostico>();
		eventos1.put(fecha6, diagnostico5);
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getEventos()).thenReturn(eventos1);
		when(historia2.getEventos()).thenReturn(eventos2);
		when(historia3.getEventos()).thenReturn(eventos3);
		
	    List<Diagnostico> diagnosticoReturn = profesional.quienesTuvieronUnaEnfermedadTambienTuvieron(diagnostico1, sistema);	
	
	    assertTrue(diagnosticoReturn.containsAll(Arrays.asList(diagnostico2,diagnostico3,diagnostico4)));
	}
	
	public void testAgregarSintomaADiagnostico(){
		
		List<Diagnostico> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
		Sintoma sintoma1 = mock(Sintoma.class);
		
		profesional.agregarSintomaADiagnostico(sintoma1, diagnostico2, sistema);
		
		verify(sistema).getDiagnosticos();
		verify(diagnostico2).getSintomas();
		
	}
	
	public void testConfirmarDiagnosticoParaPacienteConPaciente() throws PacienteNoEncontradoException{
		
		GregorianCalendar fecha = new GregorianCalendar(1,2,3);
		
		when(sistema.existePaciente(paciente)).thenReturn(true);
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
		
		profesional.confirmarDiagnosticoParaPaciente(paciente, diagnostico1, fecha, sistema);
				
		verify(historia2).agregarEvento(fecha, diagnostico1);
	}
	
	public void testRegistrarseEnElSistema() throws NombreDeUsuarioYaTomado{
		
		profesional.registrarseEnElSistema(sistema, "Fernando", "Di meglio", "3333333", "ferdoc", "1234");
		verify(sistema).darDeAltaNuevoUsuarioProfesional("Fernando", "Di meglio", "3333333", "ferdoc", "1234");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteEnElSistema() throws NombreDeUsuarioYaTomado{
		
		profesional.darDeAltaNuevoUsuarioPacienteEnElSistema("Fernando", "Di meglio", "3333333", "ferdoc", "1234", 70, 190, sistema);
		sistema.darDeAltaNuevoUsuarioPaciente("Fernando", "Di meglio", "3333333", "ferdoc", "1234", 70, 190);
	}
}
