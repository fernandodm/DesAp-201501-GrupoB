package ar.edu.unq.desapp.groupb.model.test;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Medicine;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class TreatmentTest extends TestCase {
	
	Treatment tratamiento;
	
	public void setUp(){
		tratamiento = new Treatment(true, "Parcial", 2);
	}
	
	public void testAgregarMedicamento(){
		
		tratamiento.agregarMedicamento("amoxicilina", 500, 2);
		
		//Obtengo el medicamento recien agregado
		Medicine medicamento = tratamiento.getMedicamentos().get(0); 
		
		assert(medicamento.getConcentracion() == 500);
		assert(medicamento.getDroga() == "amoxicilina");
		assert(medicamento.getTiempo() == 2);
	}
	
	public void testAgregarPracticaMedica(){
		
		tratamiento.agregarPracticaMedica("Cirugia");
		
		assert(tratamiento.getPracticasMedicas().contains("Cirugia"));
	}
}
