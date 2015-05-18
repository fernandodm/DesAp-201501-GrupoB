package ar.edu.unq.desapp.groupb.model;

import ar.edu.unq.desapp.groupb.model.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class System extends Entity {

	private List<Person> users = new ArrayList<Person>();
	private List<Diagnostic> diagnoses = new ArrayList<Diagnostic>();
	private List<MedicalHistory> medicalHistories = new ArrayList<MedicalHistory>();
	
	public System(){}
	
	public List<Person> getUsers() {
		return users;
	}
	public void setUsers(List<Person> usuarios) {
		this.users = usuarios;
	}
	public List<Diagnostic> getDiagnoses() {
		return diagnoses;
	}
	public void setDiagnoses(List<Diagnostic> diagnosticos) {
		this.diagnoses = diagnosticos;
	}
	public List<MedicalHistory> getMedicalHistories() {
		return medicalHistories;
	}
	public void setMedicalHistories(List<MedicalHistory> historias) {
		this.medicalHistories = historias;
	}
	
	public void registerNewProfessionalUser(String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		
		if(!this.thereIsUserWithName(usuario)){
			Professional profesional = new Professional(nombre, apellido, dni, usuario, contrasena);
			this.users.add(profesional);
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
		
	}
	
	public void registerNewPatientUser(String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
		if(!this.thereIsUserWithName(usuario)){
			Patient paciente = new Patient(nombre, apellido, dni, usuario, contrasena);
			this.users.add(paciente);
			this.getMedicalHistories().add(new MedicalHistory(peso,altura,paciente));
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
			
	}
	
	
	public boolean patientExists(Patient paciente){
		return this.getUsers().contains(paciente);
	}

	
	public boolean thereIsUserWithName(String username){
		boolean hay = false;
		
		for(Person each : this.getUsers()){
			if(each.getUsername().equals(username)){
				hay = true;
				break;
			}
		}
		
		return hay;
	}
	
	public Person userWithName(String username) throws PacienteNoEncontradoException{
		for(Person each : this.getUsers()){
			if(each.getUsername().equals(username)){
				return each;
			}
		}
		throw new PacienteNoEncontradoException();
	}
	
	
		
	public boolean loginSuccess(String usuario, String password) throws PacienteNoEncontradoException{
		
		if(this.thereIsUserWithName(usuario)){
			if(password.equals(this.userWithName(usuario).getPassword())){
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
	}
	
	
	public HashMap<String,Float> porcentageOfAilmentsInTheLastMonths(int meses){
		
		HashMap<String,Float> reporte = new HashMap<String,Float>();
		int cantidadDeDiagnosticos = 0;
		List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		List<String> nombreDeSintomas = new ArrayList<String>();
		GregorianCalendar diaActual = new GregorianCalendar();
		
		int dia = diaActual.get(GregorianCalendar.DATE);
		int mes = diaActual.get(GregorianCalendar.MONTH);
		int anio = diaActual.get(GregorianCalendar.YEAR);
		
		GregorianCalendar haceUnosMeses = new GregorianCalendar(anio,mes-meses,dia);
	
		cantidadDeDiagnosticos = this.getNumberOfDiagnosesSince(haceUnosMeses);
		diagnosticos = this.getDiagnosesSince(haceUnosMeses);
		nombreDeSintomas = this.getSympthomsSince(haceUnosMeses, diagnosticos);
		
		Set<String> nombreDeLosSintomas = new HashSet<String>(nombreDeSintomas);
		
		int frecuenciaDelSintoma;
		for(String nombre : nombreDeLosSintomas){
			frecuenciaDelSintoma = Collections.frequency(nombreDeSintomas, nombre);
			reporte.put(nombre, this.porcentageAmount(frecuenciaDelSintoma, cantidadDeDiagnosticos));
		}
		
		return reporte;
	}
	
	private List<String> getSympthomsSince(GregorianCalendar haceUnosMeses, List<Diagnostic> diagnosticos) {
		
		List<String> sintomas = new ArrayList<String>();
		for(Diagnostic each : diagnosticos)
			sintomas.addAll(each.listaSintomas());
		
		return sintomas;
	}
	
	private List<Diagnostic> getDiagnosesSince(
			GregorianCalendar haceUnosMeses) {
		
		List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		for(MedicalHistory each : this.getMedicalHistories())
			diagnosticos.addAll(each.eventsFromDate(haceUnosMeses));
		
		
		return diagnosticos;
	}
	
	private int getNumberOfDiagnosesSince(
			GregorianCalendar haceUnosMeses) {
		
		int cantidadDeDiagnosticos = 0;
		for(MedicalHistory each : this.getMedicalHistories()){
			cantidadDeDiagnosticos = cantidadDeDiagnosticos + each.eventsFromDate(haceUnosMeses).size();
		}
		
		return cantidadDeDiagnosticos;
	}
	
	public float porcentageAmount(int a, int b){
		
		return (a * 100) / b;
		
	}
}
