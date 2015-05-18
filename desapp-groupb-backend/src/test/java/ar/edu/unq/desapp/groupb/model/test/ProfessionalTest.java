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
import ar.edu.unq.desapp.groupb.model.System;
import ar.edu.unq.desapp.groupb.model.Treatment;
import ar.edu.unq.desapp.groupb.model.exceptions.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.groupb.model.exceptions.PacienteNoEncontradoException;

public class ProfessionalTest extends TestCase {
	Professional professional;
	System system;
	Patient patient;
	Patient patient2;
	Diagnostic diagnostic1;
	Diagnostic diagnostic2;
	Diagnostic diagnostic3;
	Diagnostic diagnostic4;
	MedicalHistory history1;
	MedicalHistory history2;
	List<MedicalHistory> histories;
	
	public void setUp(){
		professional = new Professional("Fernando", "Tolaba", "35666897", "fer11", "1234");
		system = mock(System.class);
		patient = mock(Patient.class);
		patient2 = mock(Patient.class);
		history1 = mock(MedicalHistory.class);
		history2 = mock(MedicalHistory.class);
		diagnostic1 = mock(Diagnostic.class);
		diagnostic2 = mock(Diagnostic.class);
		diagnostic3 = mock(Diagnostic.class);
		diagnostic4 = mock(Diagnostic.class);
		histories = Arrays.asList(history1, history2);
	}
	
	
	public void testObtenerHistoriaClinicaDeConPaciente() throws PacienteNoEncontradoException{
				
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getPatient()).thenReturn(patient2);
		when(history2.getPatient()).thenReturn(patient);
				
		MedicalHistory historia = professional.getMedicalHistoryFrom(patient, system);
		
		assertTrue(historia == history2);
	}
	
	public void testObtenerHistoriaClinicaDeSinPaciente(){
		
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getPatient()).thenReturn(patient2);
		when(history2.getPatient()).thenReturn(patient);
		
		Patient paciente3 = mock(Patient.class);
				
		try {
			professional.getMedicalHistoryFrom(paciente3, system);
			fail("Se produjo un error en: testObtenerHistoriaClinicaDeSinPaciente()");
		} catch (PacienteNoEncontradoException e) {
			
		}
		
	}
	
	public void testTratamientosParaElDiagnosticoConDiagnostico() throws DiagnosticoNoEncontradoException{
		
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostic1, diagnostic2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostic2, diagnostic3, diagnostic4);

		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getDiagnostics()).thenReturn(diagnosticos1);
		when(history2.getDiagnostics()).thenReturn(diagnosticos2);
		when(diagnostic1.getTreatment()).thenReturn(tratamiento1);
		when(diagnostic2.getTreatment()).thenReturn(tratamiento2);
		when(diagnostic3.getTreatment()).thenReturn(tratamiento3);
		when(diagnostic4.getTreatment()).thenReturn(tratamiento4);
		when(diagnostic1.getName()).thenReturn("Sinositis");
		when(diagnostic2.getName()).thenReturn("Resfriado");
		when(diagnostic3.getName()).thenReturn("Gripe");
		when(diagnostic4.getName()).thenReturn("Sinositis");
				
		List<Treatment> tratamientos = professional.diagnosticTreatments("Sinositis", system);
		
		List<Treatment> tratmientoAComparar = Arrays.asList(tratamiento1, tratamiento4);
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));
	
	}
	
	public void testTratamientosParaElDiagnosticoSinDiagnostico() {
	
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostic1, diagnostic2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostic2, diagnostic3, diagnostic4);
		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getDiagnostics()).thenReturn(diagnosticos1);
		when(history2.getDiagnostics()).thenReturn(diagnosticos2);
		when(diagnostic1.getTreatment()).thenReturn(tratamiento1);
		when(diagnostic2.getTreatment()).thenReturn(tratamiento2);
		when(diagnostic3.getTreatment()).thenReturn(tratamiento3);
		when(diagnostic4.getTreatment()).thenReturn(tratamiento4);
		when(diagnostic1.getName()).thenReturn("SinoSitis");
		when(diagnostic2.getName()).thenReturn("Resfriado");
		when(diagnostic3.getName()).thenReturn("Sinositis");
		when(diagnostic4.getName()).thenReturn("sinositis");
			
		
			try {
				professional.diagnosticTreatments("Gripe porcina", system);
				fail("Se produjo un error en: testTratamientosParaElDiagnosticoSinnDiagnostico()");
			} catch (DiagnosticoNoEncontradoException e) {
				
			}
	}

	public void testTieneDiagnosticoTrue(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		when(diagnostic1.getName()).thenReturn("SinoSitis");
		when(diagnostic2.getName()).thenReturn("Resfriado");
		when(diagnostic3.getName()).thenReturn("Sinositis");
		when(diagnostic4.getName()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = professional.hasDiagnostic("RESFRIADO", system);
		
		assertTrue(tieneElDiagnostico);
	}
	
	public void testTieneDiagnosticoFalse(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		when(diagnostic1.getName()).thenReturn("SinoSitis");
		when(diagnostic2.getName()).thenReturn("Resfriado");
		when(diagnostic3.getName()).thenReturn("Sinositis");
		when(diagnostic4.getName()).thenReturn("sinositis");
		
		boolean tieneElDiagnostico = professional.hasDiagnostic("Gripe", system);
		
		assertFalse(tieneElDiagnostico);
	}

	
	public void testSugerirTratamientosParaElPaciente()throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostic1, diagnostic2);
		
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostic2, diagnostic3, diagnostic4);
		
		Treatment tratamiento1 = mock(Treatment.class);
		Treatment tratamiento2 = mock(Treatment.class);
		Treatment tratamiento3 = mock(Treatment.class);
		Treatment tratamiento4 = mock(Treatment.class);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		when(system.getMedicalHistories()).thenReturn(histories);
		
		when(history1.getDiagnostics()).thenReturn(diagnosticos1);
		when(history2.getDiagnostics()).thenReturn(diagnosticos2);
		when(diagnostic1.getTreatment()).thenReturn(tratamiento1);
		when(diagnostic2.getTreatment()).thenReturn(tratamiento2);
		when(diagnostic3.getTreatment()).thenReturn(tratamiento3);
		when(diagnostic4.getTreatment()).thenReturn(tratamiento4);
		when(diagnostic1.getName()).thenReturn("SinoSitis");
		when(diagnostic2.getName()).thenReturn("sinositis");
		when(diagnostic3.getName()).thenReturn("Gripe");
		when(diagnostic4.getName()).thenReturn("sinositis");
		
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getPatient()).thenReturn(patient2);
		when(history2.getPatient()).thenReturn(patient);
		
		when(history2.patientSupports(tratamiento1)).thenReturn(true);
		when(history2.patientSupports(tratamiento4)).thenReturn(true);
		when(history2.patientSupports(tratamiento2)).thenReturn(false);
		
			
		List<Treatment> tratamientos = professional.suggestTreatmentsToPatient(patient,"Sinositis", system);
		
		List<Treatment> tratmientoAComparar = Arrays.asList(tratamiento1,tratamiento4);
		
		assertTrue(tratamientos.size() == 2);
		assertTrue(tratamientos.containsAll(tratmientoAComparar));	

	}
	
	public void testDiagnosticosPosiblesParaSintomatologia(){
	
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		Symptom sintoma3 = mock(Symptom.class);
		Symptom sintoma4 = mock(Symptom.class);
		Symptom sintoma5 = mock(Symptom.class);
		
		List<Symptom> sintomas1 = Arrays.asList(sintoma1,sintoma2);
		List<Symptom> sintomas2 = Arrays.asList(sintoma5,sintoma4);
		List<Symptom> sintomas3 = Arrays.asList(sintoma3,sintoma2,sintoma5);
		
		when(diagnostic1.getSymptoms()).thenReturn(sintomas1);
		when(diagnostic2.getSymptoms()).thenReturn(sintomas2);
		when(diagnostic3.getSymptoms()).thenReturn(sintomas1);
		when(diagnostic4.getSymptoms()).thenReturn(sintomas3);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		
		
		List<Diagnostic> diagnosticosReturn = professional.possibleDiagnosticForSymptomatology(sintomas1, system);
		
		assertTrue(diagnosticosReturn.size() == 2);
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostic1,diagnostic3)));
	}
	
	public void testQuienesTuvieronUnSintomaTambienTuvieron(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
	
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		Symptom sintoma3 = mock(Symptom.class);
		Symptom sintoma4 = mock(Symptom.class);
		Symptom sintoma5 = mock(Symptom.class);
		
		List<Symptom> sintomas1 = Arrays.asList(sintoma1,sintoma2);
		List<Symptom> sintomas2 = Arrays.asList(sintoma5,sintoma4);
		List<Symptom> sintomas3 = Arrays.asList(sintoma3,sintoma2,sintoma5);
		
		when(diagnostic1.getSymptoms()).thenReturn(sintomas1);
		when(diagnostic2.getSymptoms()).thenReturn(sintomas2);
		when(diagnostic3.getSymptoms()).thenReturn(sintomas1);
		when(diagnostic4.getSymptoms()).thenReturn(sintomas3);
		
		List<Diagnostic> diagnosticosReturn = professional.whoHadSypmtomAlsoHad(sintoma2, system);
		
		assertTrue(diagnosticosReturn.containsAll(Arrays.asList(diagnostic4, diagnostic3, diagnostic1)));
	}
	
	public void testQuienesTuvieronUnaEnfermedadTambienTuvieron(){
		
		

		MedicalHistory historia3 = mock(MedicalHistory.class);
		Diagnostic diagnostico5 = mock(Diagnostic.class);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostic1, diagnostic2);
		List<Diagnostic> diagnosticos2 = Arrays.asList(diagnostic1, diagnostic3, diagnostic4);
		List<Diagnostic> diagnosticos3 = Arrays.asList(diagnostico5);	
		List<MedicalHistory> historias = Arrays.asList(history1, history2);
	
		
		when(system.getMedicalHistories()).thenReturn(historias);
		when(history1.getDiagnostics()).thenReturn(diagnosticos1);
		when(history2.getDiagnostics()).thenReturn(diagnosticos2);
		when(historia3.getDiagnostics()).thenReturn(diagnosticos3);
		
	    List<Diagnostic> diagnosticoReturn = professional.whoHadADiseaseAlsoHad(diagnostic1, system);	
	
	    assertTrue(diagnosticoReturn.containsAll(Arrays.asList(diagnostic2,diagnostic3,diagnostic4)));
	}
	
	public void testAgregarSintomaADiagnostico(){
		
		List<Diagnostic> diagnosticos = Arrays.asList(diagnostic1, diagnostic2, diagnostic3, diagnostic4);
		
		when(system.getDiagnoses()).thenReturn(diagnosticos);
		
		Symptom sintoma1 = mock(Symptom.class);
		
		professional.AddSymptomToDiagnostic(sintoma1, diagnostic2, system);
		
		verify(system).getDiagnoses();
		verify(diagnostic2).getSymptoms();
		
	}
	
	public void testConfirmarDiagnosticoParaPacienteConPaciente() throws PacienteNoEncontradoException{
		
		Event evento = mock(Event.class);
			
		when(system.patientExists(patient)).thenReturn(true);
		when(system.getMedicalHistories()).thenReturn(histories);
		when(history1.getPatient()).thenReturn(patient2);
		when(history2.getPatient()).thenReturn(patient);
		
		professional.confirmDiagnosticForPatient(patient, evento, system);
				
		verify(history2).addEvent(evento);
	}
	
	public void testRegistrarseEnElSistema() throws NombreDeUsuarioYaTomado{
		
		professional.register(system, "Fernando", "Di meglio", "3333333", "ferdoc", "1234");
		verify(system).registerNewProfessionalUser("Fernando", "Di meglio", "3333333", "ferdoc", "1234");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteEnElSistema() throws NombreDeUsuarioYaTomado{
		
		professional.registerUser("Fernando", "Di meglio", "3333333", "ferdoc", "1234", 70, 190, system);
		verify(system).registerNewPatientUser("Fernando", "Di meglio", "3333333", "ferdoc", "1234",70,190);
	}
}
