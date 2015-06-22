package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class DiagnosticTest extends TestCase{
	
	Diagnostic diagnostic;
	Treatment treatment;
	
	public void setUp(){
		diagnostic = new Diagnostic("Sinositis");
		treatment = mock(Treatment.class);		
	}
	
	public void testDiagnosticoConUnParametro(){
		assertTrue(diagnostic.getName().equals("Sinositis"));
	}
	
	
	public void testAgregarSintoma(){
				
		diagnostic.agregarSintoma("Tos");
		
		assertTrue(diagnostic.getSymptoms().contains("Tos"));
	}
	
	
	public void testSeRelacionaConElSintomaTrue(){
		
		List<String> sintomas = new ArrayList<String>();
		sintomas.add("Tos");
		diagnostic.setSymptoms(sintomas);
				
		assertTrue(diagnostic.seRelacionConElSintoma("Tos"));
		
	}
	
	public void testSeRelacionaConElSintomaFalse(){
		List<String> sintomas = new ArrayList<String>();
		sintomas.add("Tos");
		diagnostic.setSymptoms(sintomas);
		assertFalse(diagnostic.seRelacionConElSintoma("Fiebre"));
	}
	
	public void testEliminarSintoma(){
		List<String> sintomas = new ArrayList<String>();
		sintomas.add("Tos");
		diagnostic.setSymptoms(sintomas);
		diagnostic.eliminarSintoma("Tos");
		
		assertTrue(diagnostic.getSymptoms().isEmpty());
	}
	
	public void testSeRelacionaConSintomaFalse(){
		String sintoma1 = "Tos";
		String sintoma2 = "fiebre";
		diagnostic.agregarSintoma(sintoma1);
		assertFalse(diagnostic.seRelacionConElSintoma(sintoma2));
	}
	
}
