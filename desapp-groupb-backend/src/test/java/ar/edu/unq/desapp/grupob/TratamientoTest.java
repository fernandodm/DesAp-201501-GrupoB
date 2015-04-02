package ar.edu.unq.desapp.grupob;

import junit.framework.TestCase;

public class TratamientoTest extends TestCase {
	
	Tratamiento tratamiento;
	
	public void setUp(){
		tratamiento = new Tratamiento(true, "Parcial", 2);
	}
	
	public void testAgregarMedicamento(){
		
		tratamiento.agregarMedicamento("amoxicilina", 500, 2);
		
		//Obtengo el medicamento recien agregado
		Medicamento medicamento = tratamiento.getMedicamentos().get(0); 
		
		assert(medicamento.getConcentracion() == 500);
		assert(medicamento.getDroga() == "amoxicilina");
		assert(medicamento.getTiempo() == 2);
	}
	
	public void testAgregarPracticaMedica(){
		
		tratamiento.agregarPracticaMedica("Cirugia");
		
		assert(tratamiento.getPracticasMedicas().contains("Cirugia"));
	}
}
