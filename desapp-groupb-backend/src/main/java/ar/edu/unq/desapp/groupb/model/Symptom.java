package ar.edu.unq.desapp.groupb.model;

public class Symptom extends Entity{

	private String symptomName;
	
	public Symptom(){}
	
	public Symptom(String nombre){
		this.symptomName = nombre;
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////

	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String nombre) {
		this.symptomName = nombre;
	}
	////////////////////////////
}
