package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class DiagnosticTest extends TestCase{
	
	Diagnostic diagnostic;
	String symptom;
	Treatment treatment;
	
	public void setUp(){
		diagnostic = new Diagnostic("Sinositis");
		symptom = mock(String.class);
		treatment = mock(Treatment.class);
		diagnostic.agregarSintoma(symptom);
		
	}
	
	public void testDiagnosticoConUnParametro(){
		assert(diagnostic.getName() == "Sinositis");
	}
	
	
	public void testAgregarSintoma(){
				
		diagnostic.agregarSintoma("Tos");
		
		assert(diagnostic.getSymptoms().contains("Tos"));
	}
	
	
	public void testSeRelacionaConElSintomaTrue(){
		
		List<String> sintomas = new ArrayList<String>();
		sintomas.add(symptom);
		diagnostic.setSymptoms(sintomas);
				
		assert(diagnostic.seRelacionConElSintoma(symptom));
		
	}
	
	public void testSeRelacionaConElSintomaFalse(){
		assert(!diagnostic.seRelacionConElSintoma(symptom));
	}
	
	public void testEliminarSintoma(){
		List<String> sintomas = new ArrayList<String>();
		sintomas.add(symptom);
		diagnostic.setSymptoms(sintomas);
		diagnostic.eliminarSintoma(symptom);
		
		assert(diagnostic.getSymptoms().isEmpty());
	}
	
	public void testSeRelacionaConSintomaTrue(){
		assert(diagnostic.seRelacionConElSintoma(symptom));
	}
	
	public void testSeRelacionaConSintomaFalse(){
		String sintoma1 = mock(String.class);
		String sintoma2 = mock(String.class);
		diagnostic.agregarSintoma(sintoma1);
		assert(diagnostic.seRelacionConElSintoma(sintoma2));
	}
	
	public void testListaSintomas(){
		String sintoma1 = mock(String.class);
		String sintoma2 = mock(String.class);
		diagnostic.agregarSintoma(sintoma1);
		diagnostic.agregarSintoma(sintoma2);
		
		when(symptom).thenReturn("fiebre");
		when(sintoma1).thenReturn("tos");
		when(sintoma2).thenReturn("dolor");
		
		List<String> nombres = diagnostic.listaSintomas();
		
		assertTrue(nombres.size() == 3);
		assertTrue(nombres.containsAll(Arrays.asList("fiebre","tos","dolor")));
		
	}
}
