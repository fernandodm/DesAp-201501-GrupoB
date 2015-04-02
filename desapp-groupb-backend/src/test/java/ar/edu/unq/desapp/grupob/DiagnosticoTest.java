package ar.edu.unq.desapp.grupob;


import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class DiagnosticoTest extends TestCase{
	
	Diagnostico diagnostico;
	Sintoma sintoma;
	Tratamiento tratamiento;
	
	public void setUp(){
		diagnostico = new Diagnostico("Sinositis");
		sintoma = mock(Sintoma.class);
		tratamiento = mock(Tratamiento.class);
		
	}
	
	public void testDiagnosticoConUnParametro(){
		assert(diagnostico.getNombre() == "Sinositis");
	}
	
	public void testDiagnosticoConTresParametros(){
		List<Sintoma> sintomas = new ArrayList<Sintoma>();
		sintomas.add(sintoma);
		
		List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
		tratamientos.add(tratamiento);
		
		Diagnostico diagnostico = new Diagnostico("Sinositis", sintomas, tratamientos);
		assert(diagnostico.getNombre() == "Sinositis");
		assert(diagnostico.getSintomas().contains(sintoma));
		assert(diagnostico.getTratamientos().contains(tratamiento));
	}
	
	public void testAgregarSintoma(){
				
		diagnostico.agregarSintoma(sintoma);
		
		assert(diagnostico.getSintomas().contains(sintoma));
	}
	
	public void testAgregarTratamiento(){
		
		diagnostico.agregarTratamiento(tratamiento);
		
		assert(diagnostico.getTratamientos().contains(tratamiento));
		
	}

}
