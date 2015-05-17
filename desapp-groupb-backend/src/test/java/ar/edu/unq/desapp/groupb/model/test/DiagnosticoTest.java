package ar.edu.unq.desapp.groupb.model.test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Symptom;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class DiagnosticoTest extends TestCase{
	
	Diagnostic diagnostico;
	Symptom sintoma;
	Treatment tratamiento;
	
	public void setUp(){
		diagnostico = new Diagnostic("Sinositis");
		sintoma = mock(Symptom.class);
		tratamiento = mock(Treatment.class);
		diagnostico.agregarSintoma(sintoma);
		
	}
	
	public void testDiagnosticoConUnParametro(){
		assert(diagnostico.getNombre() == "Sinositis");
	}
	
	
	public void testAgregarSintoma(){
				
		diagnostico.agregarSintoma(sintoma);
		
		assert(diagnostico.getSintomas().contains(sintoma));
	}
	
	
	public void testSeRelacionaConElSintomaTrue(){
		
		List<Symptom> sintomas = new ArrayList<Symptom>();
		sintomas.add(sintoma);
		diagnostico.setSintomas(sintomas);
				
		assert(diagnostico.seRelacionConElSintoma(sintoma));
		
	}
	
	public void testSeRelacionaConElSintomaFalse(){
		assert(!diagnostico.seRelacionConElSintoma(sintoma));
	}
	
	public void testEliminarSintoma(){
		List<Symptom> sintomas = new ArrayList<Symptom>();
		sintomas.add(sintoma);
		diagnostico.setSintomas(sintomas);
		diagnostico.eliminarSintoma(sintoma);
		
		assert(diagnostico.getSintomas().isEmpty());
	}
	
	public void testSeRelacionaConSintomaTrue(){
		assert(diagnostico.seRelacionConElSintoma(sintoma));
	}
	
	public void testSeRelacionaConSintomaFalse(){
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		diagnostico.agregarSintoma(sintoma1);
		assert(diagnostico.seRelacionConElSintoma(sintoma2));
	}
	
	public void testListaSintomas(){
		Symptom sintoma1 = mock(Symptom.class);
		Symptom sintoma2 = mock(Symptom.class);
		diagnostico.agregarSintoma(sintoma1);
		diagnostico.agregarSintoma(sintoma2);
		
		when(sintoma.getNombre()).thenReturn("fiebre");
		when(sintoma1.getNombre()).thenReturn("tos");
		when(sintoma2.getNombre()).thenReturn("dolor");
		
		List<String> nombres = diagnostico.listaSintomas();
		
		assertTrue(nombres.size() == 3);
		assertTrue(nombres.containsAll(Arrays.asList("fiebre","tos","dolor")));
		
	}
}
