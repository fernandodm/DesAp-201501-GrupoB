package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class HistoriaClinicaTest extends TestCase {
	
	HistoriaClinica historiaClinica;
	Persona persona;
	
	public void setUp(){
		persona = mock(Persona.class);
		historiaClinica = new HistoriaClinica(60, 160, persona);
		
	}
	
	public void testAgregarAlergia(){
		
		historiaClinica.agregarAlergia("Penicilina");
		
		assert(historiaClinica.getAlergias().contains("Penicilina"));
	}
	
	public void testEsAlergicoATrue(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		alergias.add("Amoxicilina");
						
		assert(historiaClinica.esAlergicoA("Penicilina"));
	}
	
	public void testEsAlergicoAFalse(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
								
		assert(historiaClinica.esAlergicoA("Amoxicilina"));
	}
	
	public void testEliminarAlergia(){
		List<String> alergias = new ArrayList<String>();
		alergias.add("Penicilina");
		historiaClinica.eliminarAlergia("Penicilina");
		assert(historiaClinica.getAlergias().isEmpty());
	}
	
	public void testElPacienteEsCompatibleConTrue(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Tratamiento tratamiento = mock(Tratamiento.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento1.getDroga()).thenReturn("azitromicina");
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.elPacienteEsCompatibleCon(tratamiento);
		
		assert(esCompatible);		
		
	}
	
	public void testElPacienteEsCompatibleConFalse(){
		Medicamento medicamento1 = mock(Medicamento.class);
		Medicamento medicamento2 = mock(Medicamento.class);
		Tratamiento tratamiento = mock(Tratamiento.class);
		
		List<Medicamento> medicamentos = Arrays.asList(medicamento1,medicamento2);
		
		when(tratamiento.getMedicamentos()).thenReturn(medicamentos);
		when(medicamento2.getDroga()).thenReturn("amoxicilina");
		
		historiaClinica.agregarAlergia("azitromicina");
		historiaClinica.agregarAlergia("ibuprofeno");
		historiaClinica.agregarAlergia("clindamicina");
		
		boolean esCompatible = historiaClinica.elPacienteEsCompatibleCon(tratamiento);
		
		assert(!esCompatible);		
		
	}

}
