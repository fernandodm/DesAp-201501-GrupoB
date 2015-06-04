package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class Diagnostic extends Entity{
	
	private String name;
	private List<String> symptoms = new ArrayList<String>();
	private Treatment treatment;
	
	
	public Diagnostic(){}
	
	public Diagnostic(String nombre, List<String> sintomas, Treatment tratamiento){
		this.name = nombre;
		this.symptoms = sintomas;
		this.treatment = tratamiento;
	}
	
	public Diagnostic(String nombre){
		this.name = nombre;
	}
	
	public void agregarSintoma(String sintoma){
		this.symptoms.add(sintoma);
	}
	
	public boolean seRelacionConElSintoma(String symptom){
		return this.getSymptoms().contains(symptom);
	}
	
	public void eliminarSintoma(String symptom){
		this.getSymptoms().remove(symptom);
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	
	
	
	
	public List<String> listaSintomas(){
		
		List<String> ls = new ArrayList<String>();
		
		for(String each : this.getSymptoms()){
			ls.add(each);
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

	public List<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<String> symptoms) {
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
		for(String s: this.getSymptoms()){
			
			listNames.add(s);
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
