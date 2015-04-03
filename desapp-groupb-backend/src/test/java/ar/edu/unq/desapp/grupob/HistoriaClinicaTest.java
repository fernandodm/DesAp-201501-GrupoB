package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class HistoriaClinicaTest extends TestCase {
	
	HistoriaClinica historiaClinica;
	Persona persona;
	Calendar fecha;
	
	public void setUp(){
		persona = mock(Persona.class);
		fecha = Calendar.getInstance();
		historiaClinica = new HistoriaClinica(60, 160, fecha, persona);
		
	}
	
	public void testAgregarAlergia(){
		
		historiaClinica.agregarAlergia("Penicilina");
		
		assert(historiaClinica.getAlergias().contains("Penicilina"));
	}
	
	public void testAgregarDiagnostico(){
		Diagnostico diagnostico = mock(Diagnostico.class);
		historiaClinica.agregarDiagnostico(diagnostico);
		
		assert(historiaClinica.getDiagnostico().contains(diagnostico));
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

}
