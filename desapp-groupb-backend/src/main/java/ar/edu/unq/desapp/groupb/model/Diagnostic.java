package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class Diagnostic extends Entity{
	
	private String name;
	private List<String> symptoms = new ArrayList<String>();
	private Treatment treatment;
	private DateTime date;
	
	public Diagnostic(){}
	
	public Diagnostic(String nombre, List<String> sintomas, Treatment tratamiento){
		this.name = nombre;
		this.symptoms = sintomas;
		this.treatment = tratamiento;
	}
	
	public Diagnostic(String nombre, List<String> sintomas){
		this.name = nombre;
		this.symptoms = sintomas;
		
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
		
	public DateTime stringToDateTime(String date){
		List<String> list = Arrays.asList(StringUtils.split(date, "/"));
		DateTime dateTime = new DateTime(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(0)), 0, 0);
    	return dateTime;
	}
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	

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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	
	
	//////////////////////////////////////
}
