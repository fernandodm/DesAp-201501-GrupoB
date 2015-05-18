package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class MedicalHistory extends Entity {
	
	private int weight;
	private int height;
	private List<String> allergies = new ArrayList<String>();
	private List<Event> events = new ArrayList<Event>();
	private Patient patient;
		
	public MedicalHistory(){}
	
	public MedicalHistory(int peso, int altura, Patient persona){
		this.weight = peso;
		this.height = altura;
		this.setPatient(persona);
	}	
	
	/* ACLARACION: se podria poner como precondicion que la primer letra sea 
	 * mayuscula y el resto minuscula o pasar todo a mayuscula y comparar
	 * ahora voy hacer la primera opcion 
	 * */
	public boolean isAllergicTo(String alergia){
		return this.getAllergies().contains(alergia);
	}
	
	public void deleteAllergy(String alergia){
		this.getAllergies().remove(alergia);
	}
	
	public void addAllergy(String alergia){
		this.getAllergies().add(alergia);
	}
	
	public boolean patientSupports(Treatment t) {
		List<Medicine> medicamentos = t.getMedicines();
		for(Medicine m: medicamentos){
			if(this.getAllergies().contains(m.getDrugName())){
				return true;
			}
		}
		return false;
	}
	
	public List<Diagnostic> eventsFromDate(GregorianCalendar fecha){
		
		List<Diagnostic> events = new ArrayList<Diagnostic>();
		
		for(Event evento : this.getEvents()){
			if(evento.getFecha().after(fecha)){
				events.add(evento.getDiagnostic());
			}
		}
		return events;
	}
	
	public void addEvent(Event evento){
		this.getEvents().add(evento);
	}
	
	public List<Diagnostic> getDiagnostics() {
		
		List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		for(Event evento: this.getEvents()){
			diagnosticos.add(evento.getDiagnostic());
		}
	
		return diagnosticos;
	}

////////////////////////
// GETTERS AND SETTERS//
////////////////////////
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> alergies) {
		this.allergies = alergies;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}