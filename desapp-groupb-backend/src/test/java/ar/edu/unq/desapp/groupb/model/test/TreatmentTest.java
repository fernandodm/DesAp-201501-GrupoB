package ar.edu.unq.desapp.groupb.model.test;

import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Medicine;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class TreatmentTest extends TestCase {
	
	Treatment treatment;
	
	public void setUp(){
		treatment = new Treatment(true, "Parcial", 2);
	}
	
	public void testAgregarMedicamento(){
		
		treatment.agregarMedicamento("amoxicilina", 500, 2);
		
		//Obtengo el medicamento recien agregado
		Medicine medicamento = treatment.getMedicines().get(0); 
		
		assert(medicamento.getConcentration() == 500);
		assert(medicamento.getDrugName() == "amoxicilina");
		assert(medicamento.getTime() == 2);
	}
	
	public void testAgregarPracticaMedica(){
		
		treatment.agregarPracticaMedica("Cirugia");
		
		assert(treatment.getMedicalPractices().contains("Cirugia"));
	}
}
