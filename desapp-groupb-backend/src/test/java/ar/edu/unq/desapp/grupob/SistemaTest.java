package ar.edu.unq.desapp.grupob;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import junit.framework.TestCase;
import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;

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
		
		sistema.darDeAltaNuevoUsuarioProfesional("goten", "paipai", "666777", "tao", "red");
		assert(sistema.getUsuarios().get(0).getNombre() == "goten");
	}
	
	public void testDarDeAltaNuevoUsuarioPacienteFallido(){
		
		try {
			sistema.darDeAltaNuevoUsuarioProfesional("cell", "paipai", "666777", "tao", "blue");
			
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
		
		Paciente paciente = mock(Paciente.class);
		sistema.getUsuarios().add(paciente);
		
		boolean existe = sistema.existePaciente(paciente);
		
		assertTrue(existe);
		
	}
	
	public void testExistePacienteFalse(){
		
		Paciente paciente1 = mock(Paciente.class);
		
		Paciente paciente = mock(Paciente.class);
		sistema.getUsuarios().add(paciente);
		
		boolean existe = sistema.existePaciente(paciente1);
		
		assertTrue(!existe);
		
	}
	
	public void testHayUsuarioConNombreTrue(){
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
		when(paciente1.getUsuario()).thenReturn("Marco");
		when(paciente2.getUsuario()).thenReturn("Mark");
		when(paciente3.getUsuario()).thenReturn("Marlos");
		
		sistema.getUsuarios().add(paciente1);
		sistema.getUsuarios().add(paciente2);
		sistema.getUsuarios().add(paciente3);
		
		boolean esEl;
		try {
			esEl = sistema.usuarioConNombre("Markinios").equals(paciente1);
			fail("No hay usuario con ese nombre");
		} catch (PacienteNoEncontradoException e) {
		}
		
	}
	
	public void testLoginValidoExitoso() throws PacienteNoEncontradoException{
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Paciente paciente1 = mock(Paciente.class);
		Paciente paciente2 = mock(Paciente.class);
		Paciente paciente3 = mock(Paciente.class);
		
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
		
		Diagnostico diagnostico1 = mock(Diagnostico.class);
		Diagnostico diagnostico2 = mock(Diagnostico.class);
		Diagnostico diagnostico3 = mock(Diagnostico.class);
		
		HistoriaClinica historia1 = mock(HistoriaClinica.class);
		HistoriaClinica historia2 = mock(HistoriaClinica.class);
		
		GregorianCalendar fecha1 = mock(GregorianCalendar.class);
		GregorianCalendar fecha2 = mock(GregorianCalendar.class);
		GregorianCalendar fecha3 = mock(GregorianCalendar.class);
		GregorianCalendar fecha4 = mock(GregorianCalendar.class);
		
		Calendar c = Calendar.getInstance();
		
		int d = Integer.parseInt(Integer.toString(c.get(Calendar.DATE)));
		int m = Integer.parseInt(Integer.toString(c.get(Calendar.MONTH)));
		int a = Integer.parseInt(Integer.toString(c.get(Calendar.YEAR)));

		GregorianCalendar haceUnosMeses = new GregorianCalendar(a,m-1,d);
		
		HashMap<GregorianCalendar,Diagnostico> eventos1 = new HashMap<GregorianCalendar,Diagnostico>();
		HashMap<GregorianCalendar,Diagnostico> eventos2 = new HashMap<GregorianCalendar,Diagnostico>();
		
		
		
		when(diagnostico1.listaSintomas()).thenReturn(sintomas1);
		when(diagnostico2.listaSintomas()).thenReturn(sintomas2);
		when(diagnostico3.listaSintomas()).thenReturn(sintomas3);
		
		eventos1.put(fecha1, diagnostico1);
		eventos1.put(fecha2, diagnostico2);
		
		eventos2.put(fecha3, diagnostico3);
		
		when(historia1.eventosDesdeFecha(haceUnosMeses)).thenReturn(eventos1);
		when(historia1.eventosDesdeFecha(haceUnosMeses)).thenReturn(eventos2);
		
		sistema.getHistorias().add(historia1);
		sistema.getHistorias().add(historia2);
		
//		HashMap<String,Float> reporte = sistema.porcentajeDeDolenciasTratadasEnLosUltimosMeses(1);
		
//		assertTrue(reporte.size() == 0);
//		verify(historia2).eventosDesdeFecha(any(GregorianCalendar.class));
	}
}
