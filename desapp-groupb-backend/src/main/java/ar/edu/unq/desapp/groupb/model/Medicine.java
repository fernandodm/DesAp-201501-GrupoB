package ar.edu.unq.desapp.groupb.model;

public class Medicine extends Entity{
	
	private String drugName;
	private int concentration;
	private int time;
	
	public Medicine(){}
	
	public Medicine(String droga, int concentracion, int tiempo){
		
		this.drugName = droga;
		this.concentration = concentracion;
		this.time = tiempo;
		
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public int getConcentration() {
		return concentration;
	}

	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	////////////////////////
	//GETTERS AND SETTERS//
	////////////////////////
	

	///////////////////////////////////
}
