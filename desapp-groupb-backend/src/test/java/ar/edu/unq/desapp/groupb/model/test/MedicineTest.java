package ar.edu.unq.desapp.groupb.model.test;


import junit.framework.TestCase;
import ar.edu.unq.desapp.groupb.model.Medicine;

public class MedicineTest extends TestCase {
	
	Medicine medicine;
	
	public void setUp(){
		medicine = new Medicine("", 0, 0);
	}
	
	public void testSetDroga(){
		medicine.setDrugName("Amoxicilina");
		assert(medicine.getDrugName() == "Amoxicilina");
	}
	
	public void testSetTiempo(){
		medicine.setTime(3);
		assert(medicine.getTime() == 3);
	}

	public void testSetConcentracion(){
		medicine.setConcentration(200);
		assert(medicine.getConcentration() == 200);
	}


}
