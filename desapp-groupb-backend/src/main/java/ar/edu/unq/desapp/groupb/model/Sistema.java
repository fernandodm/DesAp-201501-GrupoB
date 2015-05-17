package ar.edu.unq.desapp.groupb.model;

import ar.edu.unq.desapp.groupb.model.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sistema extends Entity {

	private List<Persona> usuarios = new ArrayList<Persona>();
	private List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
	private List<MedicalHistory> historias = new ArrayList<MedicalHistory>();
	
	public Sistema(){}
	
	public List<Persona> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Persona> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Diagnostic> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<Diagnostic> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<MedicalHistory> getHistorias() {
		return historias;
	}
	public void setHistorias(List<MedicalHistory> historias) {
		this.historias = historias;
	}
	
	public void darDeAltaNuevoUsuarioProfesional(String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		
		if(!this.hayUsuarioConNombre(usuario)){
			Professional profesional = new Professional(nombre, apellido, dni, usuario, contrasena);
			this.usuarios.add(profesional);
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
		
	}
	
	public void darDeAltaNuevoUsuarioPaciente(String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
		if(!this.hayUsuarioConNombre(usuario)){
			Patient paciente = new Patient(nombre, apellido, dni, usuario, contrasena);
			this.usuarios.add(paciente);
			this.getHistorias().add(new MedicalHistory(peso,altura,paciente));
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
			
	}
	
	
	public boolean existePaciente(Patient paciente){
		return this.getUsuarios().contains(paciente);
	}

	
	public boolean hayUsuarioConNombre(String username){
		boolean hay = false;
		
		for(Persona each : this.getUsuarios()){
			if(each.getUsuario().equals(username)){
				hay = true;
				break;
			}
		}
		
		return hay;
	}
	
	public Persona usuarioConNombre(String username) throws PacienteNoEncontradoException{
		for(Persona each : this.getUsuarios()){
			if(each.getUsuario().equals(username)){
				return each;
			}
		}
		throw new PacienteNoEncontradoException();
	}
	
	
		
	public boolean loginValido(String usuario, String password) throws PacienteNoEncontradoException{
		
		if(this.hayUsuarioConNombre(usuario)){
			if(password.equals(this.usuarioConNombre(usuario).getContrasena())){
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
	}
	
	
	public HashMap<String,Float> porcentajeDeDolenciasEnLosUltimosMeses(int meses){
		
		HashMap<String,Float> reporte = new HashMap<String,Float>();
		int cantidadDeDiagnosticos = 0;
		List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		List<String> nombreDeSintomas = new ArrayList<String>();
		GregorianCalendar diaActual = new GregorianCalendar();
		
		int dia = diaActual.get(GregorianCalendar.DATE);
		int mes = diaActual.get(GregorianCalendar.MONTH);
		int anio = diaActual.get(GregorianCalendar.YEAR);
		
		GregorianCalendar haceUnosMeses = new GregorianCalendar(anio,mes-meses,dia);
	
		cantidadDeDiagnosticos = this.obtenerCantidadDeDiagnosticosDesde(haceUnosMeses);
		diagnosticos = this.obtenerDiagnosticosDesde(haceUnosMeses);
		nombreDeSintomas = this.obtenerSintomasDesde(haceUnosMeses, diagnosticos);
		
		Set<String> nombreDeLosSintomas = new HashSet<String>(nombreDeSintomas);
		
		int frecuenciaDelSintoma;
		for(String nombre : nombreDeLosSintomas){
			frecuenciaDelSintoma = Collections.frequency(nombreDeSintomas, nombre);
			reporte.put(nombre, this.porcentajeEnCantidad(frecuenciaDelSintoma, cantidadDeDiagnosticos));
		}
		
		return reporte;
	}
	
	private List<String> obtenerSintomasDesde(GregorianCalendar haceUnosMeses, List<Diagnostic> diagnosticos) {
		
		List<String> sintomas = new ArrayList<String>();
		for(Diagnostic each : diagnosticos)
			sintomas.addAll(each.listaSintomas());
		
		return sintomas;
	}
	
	private List<Diagnostic> obtenerDiagnosticosDesde(
			GregorianCalendar haceUnosMeses) {
		
		List<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		for(MedicalHistory each : this.getHistorias())
			diagnosticos.addAll(each.eventsFromDate(haceUnosMeses));
		
		
		return diagnosticos;
	}
	
	private int obtenerCantidadDeDiagnosticosDesde(
			GregorianCalendar haceUnosMeses) {
		
		int cantidadDeDiagnosticos = 0;
		for(MedicalHistory each : this.getHistorias()){
			cantidadDeDiagnosticos = cantidadDeDiagnosticos + each.eventsFromDate(haceUnosMeses).size();
		}
		
		return cantidadDeDiagnosticos;
	}
	
	public float porcentajeEnCantidad(int a, int b){
		
		return (a * 100) / b;
		
	}
}
