package ar.edu.unq.desapp.grupob;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import junit.framework.TestCase;
import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;



public class PacienteTest extends TestCase {

	Paciente paciente;
	
	public void setUp(){
		paciente = new Paciente("Esteban","Di Meglia","36811371","edm","amdamd");
	}
	
	public void testRegistrarEnElSistema() throws NombreDeUsuarioYaTomado{
		
		
		Sistema system = mock(Sistema.class);
		ArrayList<Persona> users =  new ArrayList<Persona>();
		
		when(system.getUsuarios()).thenReturn(users);
		
		paciente.registrarEnElSistema(system, paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getUsuario(), paciente.getContrasena(), 85, 180);
		
		verify(system).darDeAltaNuevoUsuarioPaciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getUsuario(), paciente.getContrasena(), 85, 180);
	}
	
	
}
