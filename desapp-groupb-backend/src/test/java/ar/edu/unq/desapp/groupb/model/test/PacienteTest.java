package ar.edu.unq.desapp.groupb.model.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Persona;
import ar.edu.unq.desapp.groupb.model.Sistema;
import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;



public class PacienteTest extends TestCase {

	Patient paciente;
	
	public void setUp(){
		paciente = new Patient("Esteban","Di Meglia","36811371","edm","amdamd");
	}
	
	public void testRegistrarEnElSistema() throws NombreDeUsuarioYaTomado{
		
		
		Sistema system = mock(Sistema.class);
		ArrayList<Persona> users =  new ArrayList<Persona>();
		
		when(system.getUsuarios()).thenReturn(users);
		
		paciente.registrarEnElSistema(system, paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getUsuario(), paciente.getContrasena(), 85, 180);
		
		verify(system).darDeAltaNuevoUsuarioPaciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getUsuario(), paciente.getContrasena(), 85, 180);
	}
	
	
}
