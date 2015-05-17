package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.desapp.groupb.model.exceptions.*;

public class Professional extends Person {

	public Professional(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
	}
	
	public Professional(){}

	public void register(Sistema sistema, String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		sistema.darDeAltaNuevoUsuarioProfesional(nombre, apellido, dni, usuario, contrasena);
	}
	
	public void registerUser(String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura, Sistema sistema) throws NombreDeUsuarioYaTomado{
		
		sistema.darDeAltaNuevoUsuarioPaciente(nombre, apellido, dni, usuario, contrasena, peso, altura);
	}
	
	
	public MedicalHistory getMedicalHistoryFrom(Person persona, Sistema sistema) throws PacienteNoEncontradoException{
		
		for(MedicalHistory h : sistema.getHistorias()){
			if(h.getPatient().equals(persona)){
				return h;
			}
	}
				
		throw new PacienteNoEncontradoException();
	}
	
	public List<Treatment> diagnosticTreatments(String nombre, Sistema sistema) throws DiagnosticoNoEncontradoException{
		
		Set<Treatment> tratamientos = new HashSet<Treatment>();
		
		if(this.hasDiagnostic(nombre, sistema)){
			for(MedicalHistory h: sistema.getHistorias()){
				for(Diagnostic d: h.getDiagnostics()){
					if(d.getName().toUpperCase().equals(nombre.toUpperCase())){
						tratamientos.add(d.getTreatment());
					}
				}
			}
			ArrayList<Treatment> tratamientoList = new ArrayList<Treatment>(tratamientos);
			return tratamientoList;
		} else {
			throw new DiagnosticoNoEncontradoException();
		}
	}
	
	public boolean hasDiagnostic(String nombre, Sistema sistema){
		for(Diagnostic d: sistema.getDiagnosticos()){
			if(d.getName().toUpperCase().equals(nombre.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}
	
	
	public List<Treatment> suggestTreatmentsToPatient(Patient paciente,String nombreDiagnostico, Sistema sistema) throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		List<Treatment> tratamientos = this.diagnosticTreatments(nombreDiagnostico, sistema);
		MedicalHistory historia = this.getMedicalHistoryFrom(paciente, sistema);
		List<Treatment> tratamientosReturn = new ArrayList<Treatment>();
		for(Treatment t: tratamientos){
			if(historia.patientSupports(t)){
				tratamientosReturn.add(t);
			}
		}
		return tratamientosReturn;
	}
	
	
	public List<Diagnostic> diagnosticosPosiblesParaSintomatologia(List<Symptom> sintomatologia, Sistema sistema){
		
		ArrayList<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		
		for(Diagnostic each : sistema.getDiagnosticos()){
			if(each.getSymptoms().containsAll(sintomatologia)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
		
	}
	
	public List<Diagnostic> quienesTuvieronUnSintomaTambienTuvieron(Symptom sintoma, Sistema sistema){
		
		ArrayList<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		
		for(Diagnostic each : sistema.getDiagnosticos()){
			if(each.getSymptoms().contains(sintoma)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
	}
	
	public List<Diagnostic> quienesTuvieronUnaEnfermedadTambienTuvieron(Diagnostic diagnostico, Sistema sistema){
		
		Set<Diagnostic> diagnosticos = new HashSet<Diagnostic>();
		
		for(MedicalHistory each : sistema.getHistorias()){
			if(each.getDiagnostics().contains(diagnostico)){
				for(Diagnostic d : each.getDiagnostics()){
					diagnosticos.add(d);
				}
			}
		}
		
		ArrayList<Diagnostic> diagnosticosPosta = new ArrayList<Diagnostic>(diagnosticos);
		
		return diagnosticosPosta;
		
	}
	
	public void agregarSintomaADiagnostico(Symptom sintoma, Diagnostic diagnostico, Sistema sistema){
		
		for(Diagnostic each :  sistema.getDiagnosticos()){
			if(diagnostico.equals(each)){
				each.getSymptoms().add(sintoma);
			}
		}
		
	}
	
	public void confirmarDiagnosticoParaPaciente(Patient paciente, Event evento, Sistema sistema) throws PacienteNoEncontradoException{
		
		if(sistema.existePaciente(paciente)){
			this.getMedicalHistoryFrom(paciente, sistema).addEvent(evento);
	
		}else{
			throw new PacienteNoEncontradoException();
		}
	}
}