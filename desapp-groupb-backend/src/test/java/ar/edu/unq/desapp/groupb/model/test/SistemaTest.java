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
import ar.edu.unq.desapp.groupb.model.Sistema;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.groupb.model.exceptions.PacienteNoEncontradoException;

public class SistemaTest extends TestCase {

	Sistema sistema;
	
	public void setUp(){
		sistema = new Sistema();
	}
	
	public void testDarDeAltaNuevoUsuarioProfesionalExitoso() throws NombreDeUsuarioYaTomado{
		
		sistema.darDeAltaNuevoUsuarioProfesional("goku", "paipai", "666777", "tao", "blue");
		assertTrue(sistema.getUsuarios().size() == 1);
	}
	
	public void testDarDeAltaNuevoUsuarioProfesionalFallido(){
		
		try {
			sistema.darDeAltaNuevoUsuarioProfesional("goku", "paipai", "666777", "tao", "blue");
			
		} catch (NombreDeUsuarioYaTomado e) {
			e.printStackTrace();
		}
		try {
			sistema.darDeAltaNuevoUsuarioProfesional("goku", "paipai", "666777", "tao", "blue");
			fail("El usuario ya existe");
		} catch (NombreDeUsuarioYaTomado e) {

		}
		assertTrue(sistema.getUsuarios().size() == 1);
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteExitoso() throws NombreDeUsuarioYaTomado{
		
		sistema.darDeAltaNuevoUsuarioPaciente("goten", "paipai", "666777", "tao", "red",60,189);
		assert(sistema.getUsuarios().get(0).getNombre() == "goten");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteFallido(){
		
		try {
			sistema.darDeAltaNuevoUsuarioPaciente("cell", "paipai", "666777", "tao", "blue",60,189);
			
		} catch (NombreDeUsuarioYaTomado e) {
			e.printStackTrace();
		}
		try {
			sistema.darDeAltaNuevoUsuarioProfesional("cell", "paipai", "666777", "tao", "blue");
			fail("El usuario ya existe");
		} catch (NombreDeUsuarioYaTomado e) {

		}
		assertTrue(sistema.getUsuarios().size() == 1);
	}
	
	public void testExistePacienteTrue(){
		
		Patient paciente = mock(Patient.class);
		sistema.getUsuarios().add(paciente);
		
		boolean existe = sistema.existePaciente(paciente);
		
		assertTrue(existe);
		
	}
	
	public void testExistePacienteFalse(){
		
		Patient paciente1 = mock(Patient.class);
		
		Patient paciente = mock(Patient.class);
		sistema.getUsuarios().add(paciente);
		
		boolean existe = sistema.existePaciente(paciente1);
		
		assertTrue(!existe);
		
	}
	
	public void testHayUsuarioConNombreTrue(){
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		boolean hay = sistema.hayUsuarioConNombre("Mark");
		assertTrue(hay);
	}
	
	public void testHayUsuarioConNombreFalse(){
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		boolean hay = sistema.hayUsuarioConNombre("Markitos");
		assertFalse(hay);
	}
	
	public void testUsuarioConNombreExito() throws PacienteNoEncontradoException{
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		boolean esEl = sistema.usuarioConNombre("Marco").equals(paciente1);
		
		assertTrue(esEl);
		
	}
	
	public void testUsuarioConNombreNoEncontrado() {
		
		Patient paciente1 = mock(Patient.class);
		Patient paciente2 = mock(Patient.class);
		Patient paciente3 = mock(Patient.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		try {
			sistema.usuarioConNombre("Markinios").equals(paciente1);
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
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		seLogueo = sistema.loginValido("Marco", "tor");
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
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		seLogueo = sistema.loginValido("Marsco", "tor");
			
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
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		seLogueo = sistema.loginValido("Marco", "torsiglieri");
			
		assertFalse(seLogueo);
	}
	
	public void testPorcentajeEnCantidad(){
		
		float porcent = sistema.porcentajeEnCantidad(50, 100);
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
		
		sistema.getHistorias().add(historia1);
		sistema.getHistorias().add(historia2);
		
		HashMap<String,Float> reporte = sistema.porcentajeDeDolenciasEnLosUltimosMeses(12);
		
		assertTrue(reporte.size() == 4);
	}
}
