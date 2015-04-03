package ar.edu.unq.desapp.grupob;

import junit.framework.TestCase;

public class MedicamentoTest extends TestCase {
	
	Medicamento medicamento;
	
	public void setUp(){
		medicamento = new Medicamento("", 0, 0);
	}
	
	public void testSetDroga(){
		medicamento.setDroga("Amoxicilina");
		assert(medicamento.getDroga() == "Amoxicilina");
	}
	
	public void testSetTiempo(){
		medicamento.setTiempo(3);
		assert(medicamento.getTiempo() == 3);
	}

	public void testSetConcentracion(){
		medicamento.setConcentracion(200);
		assert(medicamento.getConcentracion() == 200);
	}


}
