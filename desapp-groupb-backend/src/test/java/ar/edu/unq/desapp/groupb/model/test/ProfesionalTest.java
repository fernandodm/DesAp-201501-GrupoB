package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Professional;
import ar.edu.unq.desapp.groupb.model.Symptom;
import ar.edu.unq.desapp.groupb.model.Sistema;
import ar.edu.unq.desapp.groupb.model.Treatment;
import ar.edu.unq.desapp.groupb.model.exceptions.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.groupb.model.exceptions.PacienteNoEncontradoException;

public class ProfesionalTest extends TestCase {
	Professional profesional;
	Sistema sistema;
	Patient paciente;
	Patient paciente2;
	Diagnostic diagnostico1;
	Diagnostic diagnostico2;
	Diagnostic diagnostico3;
	Diagnostic diagnostico4;
	MedicalHistory historia1;
	MedicalHistory historia2;
	List<MedicalHistory> historias;
	
	public void setUp(){
		profesional = new Professional("Fernando", "Tolaba", "35666897", "fer11", "1234");
		sistema = mock(Sistema.class);
		paciente = mock(Patient.class);
		paciente2 = mock(Patient.class);
		historia1 = mock(MedicalHistory.class);
		historia2 = mock(MedicalHistory.class);
		diagnostico1 = mock(Diagnostic.class);
		diagnostico2 = mock(Diagnostic.class);
		diagnostico3 = mock(Diagnostic.class);
		diagnostico4 = mock(Diagnostic.class);
		historias = Arrays.asList(historia1, historia2);
	}
	
	
	public void testObtenerHistoriaClinicaDeConPaciente() throws PacienteNoEncontradoException{
				
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
				
		MedicalHistory historia = profesional.getMedicalHistoryFrom(paciente, sistema);
		
		assertTrue(historia == historia2);
	}
	
	public void testObtenerHistoriaClinicaDeSinPaciente(){
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
		
		Patient paciente3 = mock(Patient.class);
				
		try {
			profesional.getMedicalHistoryFrom(paciente3, sistema);
			fail("Se produjo un error en: testObtenerHistoriaClinicaDeSinPaciente()");
		} catch (PacienteNoEncontradoException e) {
			
		}
		
	}
	
	public void testTratamientosParaElDiagnosticoConDiagnostico() throws DiagnosticoNoEncontradoException{
		
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostico1, diagnostico2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostico2, diagnostico3, diagnostico4);

		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getDiagnostics()).thenReturn(diagnosticos1);
		when(historia2.getDiagnostics()).thenReturn(diagnosticos2);
		when(diagnostico1.getTratamiento()).thenReturn(tratamiento1);
		when(diagnostico2.getTratamiento()).thenReturn(tratamiento2);
		when(diagnostico3.getTratamiento()).thenReturn(tratamiento3);
		when(diagnostico4.getTratamiento()).thenReturn(tratamiento4);
		when(diagnostico1.getNombre()).thenReturn("Sinositis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Gripe");
		when(diagnostico4.getNombre()).thenReturn("Sinositis");
				
		List<Treatment> tratamientos = profesional.diagnosticTreatments("Sinositis", sistema);
		
		List<Treatment> tratmientoAComparar = Arrays.asList(tratamiento1, tratamiento4);
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));
	
	}
	
	public void testTratamientosParaElDiagnosticoSinDiagnostico() {
	
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostico1, diagnostico2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostico2, diagnostico3, diagnostico4);
		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getDiagnostics()).thenReturn(diagnosticos1);
		when(historia2.getDiagnostics()).thenReturn(diagnosticos2);
		when(diagnostico1.getTratamiento()).thenReturn(tratamiento1);
		when(diagnostico2.getTratamiento()).thenReturn(tratamiento2);
		when(diagnostico3.getTratamiento()).thenReturn(tratamiento3);
		when(diagnostico4.getTratamiento()).thenReturn(tratamiento4);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Sinositis");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
			
		
			try {
				profesional.diagnosticTreatments("Gripe porcina", sistema);
				fail("Se produjo un error en: testTratamientosParaElDiagnosticoSinnDiagnostico()");
			} catch (DiagnosticoNoEncontradoException e) {
				
			}
	}

	public void testTieneDiagnosticoTrue(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Sinositis");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = profesional.hasDiagnostic("RESFRIADO", sistema);
		
		assertTrue(tieneElDiagnostico);
	}
	
	public void testTieneDiagnosticoFalse(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(diagnostico1.getNombre()).thenReturn("SinoSitis");
		when(diagnostico2.getNombre()).thenReturn("Resfriado");
		when(diagnostico3.getNombre()).thenReturn("Sinositis");
		when(diagnostico4.getNombre()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = profesional.hasDiagnostic("Gripe", sistema);
		
		assertFalse(tieneElDiagnostico);
	}

	
	public void testSugerirTratamientosParaElPaciente()throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostico1, diagnostico2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostico2, diagnostico3, diagnostico4);
		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		when(sistema.getHistorias()).thenReturn(historias);
		
		when(historia1.getDiagnostics()).thenReturn(diagnosticos1);
		when(historia2.getDiagnostics()).thenReturn(diagnosticos2);
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
		
		when(historia2.patientSupports(tratamiento1)).thenReturn(true);
		when(historia2.patientSupports(tratamiento4)).thenReturn(true);
		when(historia2.patientSupports(tratamiento2)).thenReturn(false);
		
			
		List<Treatment> tratamientos = profesional.suggestTreatmentsToPatient(paciente,"Sinositis", sistema);
		
		List<Treatment> tratmientoAComparar = Arrays.asList(tratamiento1,tratamiento4);
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));	

	}
	
	public void testDiagnosticosPosiblesParaSintomatologia(){
	
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		Symptom sintoma3 = mock(Symptom.class);
		Symptom sintoma4 = mock(Symptom.class);
		Symptom sintoma5 = mock(Symptom.class);
		
		List<Symptom> sintomas1 = Arrays.asList(sintoma1,sintoma2);
		List<Symptom> sintomas2 = Arrays.asList(sintoma5,sintoma4);
		List<Symptom> sintomas3 = Arrays.asList(sintoma3,sintoma2,sintoma5);
		
		when(diagnostico1.getSintomas()).thenReturn(sintomas1);
		when(diagnostico2.getSintomas()).thenReturn(sintomas2);
		when(diagnostico3.getSintomas()).thenReturn(sintomas1);
		when(diagnostico4.getSintomas()).thenReturn(sintomas3);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
		
		List<Diagnostic> diagnosticosReturn = profesional.diagnosticosPosiblesParaSintomatologia(sintomas1, sistema);
		
		assertTrue(diagnosticosReturn.size() == 2);
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico1,diagnostico3)));
	}
	
	public void testQuienesTuvieronUnSintomaTambienTuvieron(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
	
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		Symptom sintoma3 = mock(Symptom.class);
		Symptom sintoma4 = mock(Symptom.class);
		Symptom sintoma5 = mock(Symptom.class);
		
		List<Symptom> sintomas1 = Arrays.asList(sintoma1,sintoma2);
		List<Symptom> sintomas2 = Arrays.asList(sintoma5,sintoma4);
		List<Symptom> sintomas3 = Arrays.asList(sintoma3,sintoma2,sintoma5);
		
		when(diagnostico1.getSintomas()).thenReturn(sintomas1);
		when(diagnostico2.getSintomas()).thenReturn(sintomas2);
		when(diagnostico3.getSintomas()).thenReturn(sintomas1);
		when(diagnostico4.getSintomas()).thenReturn(sintomas3);
		
		List<Diagnostic> diagnosticosReturn = profesional.quienesTuvieronUnSintomaTambienTuvieron(sintoma2, sistema);
		
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostico4, diagnostico3, diagnostico1)));
	}
	
	public void testQuienesTuvieronUnaEnfermedadTambienTuvieron(){
		
		

		MedicalHistory historia3 = mock(MedicalHistory.class);
		Diagnostic diagnostico5 = mock(Diagnostic.class);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostico1, diagnostico2);
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostico1, diagnostico3, diagnostico4);
		List<Diagnostic> diagnosticos3 = Arrays.asList(diagnostico5);	
		List<MedicalHistory> historias = Arrays.asList(historia1, historia2);
	
		
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getDiagnostics()).thenReturn(diagnosticos1);
		when(historia2.getDiagnostics()).thenReturn(diagnosticos2);
		when(historia3.getDiagnostics()).thenReturn(diagnosticos3);
		
	    List<Diagnostic> diagnosticoReturn = profesional.whoHadADiseaseAlsoHad(diagnostico1, sistema);	
	
	    assertTrue(diagnosticoReturn.containsAll(Arrays.asList(diagnostico2,diagnostico3,diagnostico4)));
	}
	
	public void testAgregarSintomaADiagnostico(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostico1, diagnostico2, diagnostico3, diagnostico4);
		
		when(sistema.getDiagnosticos()).thenReturn(diagnosticos);
		
		Symptom sintoma1 = mock(Symptom.class);
		
		profesional.AddSymptomToDiagnostic(sintoma1, diagnostico2, sistema);
		
		verify(sistema).getDiagnosticos();
		verify(diagnostico2).getSintomas();
		
	}
	
	public void testConfirmarDiagnosticoParaPacienteConPaciente() throws PacienteNoEncontradoException{
		
		Event evento = mock(Event.class);
			
		when(sistema.existePaciente(paciente)).thenReturn(true);
		when(sistema.getHistorias()).thenReturn(historias);
		when(historia1.getPersona()).thenReturn(paciente2);
		when(historia2.getPersona()).thenReturn(paciente);
		
		profesional.confirmDiagnosticForPatient(paciente, evento, sistema);
				
		verify(historia2).addEvent(evento);
	}
	
	public void testRegistrarseEnElSistema() throws NombreDeUsuarioYaTomado{
		
		profesional.register(sistema, "Fernando", "Di meglio", "3333333", "ferdoc", "1234");
		verify(sistema).darDeAltaNuevoUsuarioProfesional("Fernando", "Di meglio", "3333333", "ferdoc", "1234");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteEnElSistema() throws NombreDeUsuarioYaTomado{
		
		profesional.registerUser("Fernando", "Di meglio", "3333333", "ferdoc", "1234", 70, 190, sistema);
		verify(sistema).darDeAltaNuevoUsuarioPaciente("Fernando", "Di meglio", "3333333", "ferdoc", "1234",70,190);
	}
}
