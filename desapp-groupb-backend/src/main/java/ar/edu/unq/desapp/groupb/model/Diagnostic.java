package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class Diagnostic extends Entity{
	
	private String name;
	private List<Symptom> symptoms = new ArrayList<Symptom>();
	private Treatment treatment;
	
	
	public Diagnostic(){}
	
	public Diagnostic(String nombre, List<Symptom> sintomas, Treatment tratamiento){
		this.name = nombre;
		this.symptoms = sintomas;
		this.treatment = tratamiento;
	}
	
	public Diagnostic(String nombre){
		this.name = nombre;
	}
	
	public void agregarSintoma(Symptom sintoma){
		this.symptoms.add(sintoma);
	}
	
	public boolean seRelacionConElSintoma(Symptom sintoma){
		return this.getSymptoms().contains(sintoma);
	}
	
	public void eliminarSintoma(Symptom sintoma){
		this.getSymptoms().remove(sintoma);
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	
	
	
	
	public List<String> listaSintomas(){
		
		List<String> ls = new ArrayList<String>();
		
		for(Symptom each : this.getSymptoms()){
			ls.add(each.getSymptomName());
		}
		
		return ls;
		
	}
	//////////////////////////////////////

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public List<String> getSymptomsNames() {
		List<String> listNames = new ArrayList<String>();
		for(Symptom s: this.getSymptoms()){
			
			listNames.add(s.getSymptomName().trim());
		}
		return listNames;
	}
	
	public boolean containsSymptoms(List <String> symps){
		
		boolean ret = false;
			
		for(String each : symps){
			for(String s: this.getSymptomsNames()){
					if(each.equals(s)){
						return true;
						
					}else{
						java.lang.System.out.println(each + " " + s);
						ret = false;
					}
			}
		}
		
		
		return ret;
		
	}
	
	public void deleteSymptom(String symptom){
		
	}
	

	
}
