package ar.edu.unq.desapp.grupob;


import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class DiagnosticoTest extends TestCase{
	
	public void testAgregarSintoma(){
		
		Diagnostico diagnostico = new Diagnostico("Sinositis");
		
		Sintoma sintoma = mock(Sintoma.class);
		
		diagnostico.agregarSintoma(sintoma);
		
		assert(diagnostico.getSintomas().contains(sintoma));
	}

}
