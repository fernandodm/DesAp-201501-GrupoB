package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class Symptom extends Entity{

	private String symptomName;
	private List<Diagnostic> diagnostics =  new ArrayList<Diagnostic>();
	
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
	
	public List<Diagnostic> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(List<Diagnostic> diagnostics) {
		this.diagnostics = diagnostics;
	}
	////////////////////////////
	
}
