package ar.edu.unq.desapp.groupb.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.System;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.groupb.model.exceptions.PacienteNoEncontradoException;

public class SistemaTest extends TestCase {

	System sistema;
	
	public void setUp(){
		sistema = new System();
	}
	
	public void testDarDeAltaNuevoUsuarioProfesionalExitoso() throws NombreDeUsuarioYaTomado{
		
		sistema.registerNewProfessionalUser("goku", "paipai", "666777", "tao", "blue");
		assertTrue(sistema.getUsers().size() == 1);
	}
	
	public void testDarDeAltaNuevoUsuarioProfesionalFallido(){
		
		try {
			sistema.registerNewProfessionalUser("goku", "paipai", "666777", "tao", "blue");
			
		} catch (NombreDeUsuarioYaTomado e) {
			e.printStackTrace();
		}
		try {
			sistema.registerNewProfessionalUser("goku", "paipai", "666777", "tao", "blue");
			fail("El usuario ya existe");
		} catch (NombreDeUsuarioYaTomado e) {

		}
		assertTrue(sistema.getUsers().size() == 1);
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteExitoso() throws NombreDeUsuarioYaTomado{
		
		sistema.registerNewPatientUser("goten", "paipai", "666777", "tao", "red",60,189);
		assert(sistema.getUsers().get(0).getNombre() == "goten");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteFallido(){
		
		try {
			sistema.registerNewPatientUser("cell", "paipai", "666777", "tao", "blue",60,189);
			
		} catch (NombreDeUsuarioYaTomado e) {
			e.printStackTrace();
		}
		try {
			sistema.registerNewProfessionalUser("cell", "paipai", "666777", "tao", "blue");
			fail("El usuario ya existe");
		} catch (NombreDeUsuarioYaTomado e) {

		}
		assertTrue(sistema.getUsers().size() == 1);
	}
	
	public void testExistePacienteTrue(){
		
		Patient paciente = mock(Patient.class);
		sistema.getUsers().add(paciente);
		
		boolean existe = sistema.patientExists(paciente);
		
		assertTrue(existe);
		
	}
	
	public void testExistePacienteFalse(){
		
		Patient paciente1 = mock(Patient.class);
		
		Patient paciente = mock(Patient.class);
		sistema.getUsers().add(paciente);
		
		boolean existe = sistema.patientExists(paciente1);
		
		assertTrue(!existe);
		
	}
	
	public void testHayUsuarioConNombreTrue(){
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		boolean hay = sistema.thereIsUserWithName("Mark");
		assertTrue(hay);
	}
	
	public void testHayUsuarioConNombreFalse(){
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		boolean hay = sistema.thereIsUserWithName("Markitos");
		assertFalse(hay);
	}
	
	public void testUsuarioConNombreExito() throws PacienteNoEncontradoException{
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		boolean esEl = sistema.userWithName("Marco").equals(paciente1);
		
		assertTrue(esEl);
		
	}
	
	public void testUsuarioConNombreNoEncontrado() {
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		try {
			sistema.userWithName("Markinios").equals(paciente1);
			fail("No hay usuario con ese nombre");
		} catch (PacienteNoEncontradoException e) {
		}
		
	}
	
	public void testLoginValidoExitoso() throws PacienteNoEncontradoException{
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		boolean seLogueo = false;
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente1.getContrasena()).thenReturn("tor");
		
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente2.getContrasena()).thenReturn("sigli");
		
		when(paciente3.getUsuario()).thenReturn("Marlos");
		when(paciente3.getContrasena()).thenReturn("eri");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		seLogueo = sistema.loginSuccess("Marco", "tor");
		assertTrue(seLogueo);
		
	}
	
	public void testLoginValidoPorUsuarioInexistente() throws PacienteNoEncontradoException{
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		boolean seLogueo = false;

		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente1.getContrasena()).thenReturn("tor");
		
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente2.getContrasena()).thenReturn("sigli");
		
		when(paciente3.getUsuario()).thenReturn("Marlos");
		when(paciente3.getContrasena()).thenReturn("eri");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		seLogueo = sistema.loginSuccess("Marsco", "tor");
			
		assertFalse(seLogueo);
	}
	
	public void testLoginValidoPorPassErronea() throws PacienteNoEncontradoException{
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		boolean seLogueo = false;

		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente1.getContrasena()).thenReturn("tor");
		
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente2.getContrasena()).thenReturn("sigli");
		
		when(paciente3.getUsuario()).thenReturn("Marlos");
		when(paciente3.getContrasena()).thenReturn("eri");
		
		sistema.getUsers().add(paciente1);
		sistema.getUsers().add(paciente2);
		sistema.getUsers().add(paciente3);
		
		seLogueo = sistema.loginSuccess("Marco", "torsiglieri");
			
		assertFalse(seLogueo);
	}
	
	public void testPorcentajeEnCantidad(){
		
		float porcent = sistema.porcentageAmount(50, 100);
		assertTrue(porcent == 50);
	}
	
	public void testPorcentajeDeDolenciasEnLosUltimosMeses(){
		
		ArrayList<String> sintomas1 = new ArrayList<String>();
		ArrayList<String> sintomas2 = new ArrayList<String>();
		ArrayList<String> sintomas3 = new ArrayList<String>();
		
		sintomas1.add("picason");
		sintomas1.add("estornudos");
		
		sintomas2.add("picason");
		sintomas2.add("diarrea");
		
		sintomas3.add("picason");
		sintomas3.add("tos");
		
		Diagnostic diagnostico1 = mock(Diagnostic.class);
		Diagnostic diagnostico2 = mock(Diagnostic.class);
		Diagnostic diagnostico3 = mock(Diagnostic.class);
		
		MedicalHistory historia1 = mock(MedicalHistory.class);
		MedicalHistory historia2 = mock(MedicalHistory.class);
		
		GregorianCalendar diaActual = new GregorianCalendar();
		
		int d = diaActual.get(GregorianCalendar.DATE);
		int m = diaActual.get(GregorianCalendar.MONTH) ;
		int a = diaActual.get(GregorianCalendar.YEAR);;

		GregorianCalendar haceUnosMeses = new GregorianCalendar(a,m-12,d);
		
		List<Diagnostic> diagnosticos1 = Arrays.asList(diagnostico1,diagnostico2);
		List<Diagnostic>  diagnosticos2 = Arrays.asList(diagnostico3);
		
	
		when(diagnostico1.listaSintomas()).thenReturn(sintomas1);
		when(diagnostico2.listaSintomas()).thenReturn(sintomas2);
		when(diagnostico3.listaSintomas()).thenReturn(sintomas3);
		
		when(historia1.eventsFromDate(haceUnosMeses)).thenReturn(diagnosticos1);
		when(historia2.eventsFromDate(haceUnosMeses)).thenReturn(diagnosticos2);
		
		sistema.getMedicalHistories().add(historia1);
		sistema.getMedicalHistories().add(historia2);
		
		HashMap<String,Float> reporte = sistema.porcentageOfAilmentsInTheLastMonths(12);
		
		assertTrue(reporte.size() == 4);
	}
}
