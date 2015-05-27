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

	public void register(System sistema, String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		sistema.registerNewProfessionalUser(nombre, apellido, dni, usuario, contrasena);
	}
	
//	public void registerUser(String nombre, String apellido, String dni,
//			String usuario, String contrasena, int peso, int altura, System sistema) throws NombreDeUsuarioYaTomado{
//		
//		sistema.registerNewPatientUser(nombre, apellido, dni, usuario, contrasena, peso, altura);
//	}
//	
	
//	public MedicalHistory getMedicalHistoryFrom(Person persona, System sistema) throws PacienteNoEncontradoException{
//		
////		for(MedicalHistory h : sistema.getMedicalHistories()){
////			if(h.getPatient().equals(persona)){
////				return h;
////			}
//	}
//				
//		throw new PacienteNoEncontradoException();
//	}
	
	public List<Treatment> diagnosticTreatments(String nombre, System sistema) throws DiagnosticoNoEncontradoException{
		
		Set<Treatment> tratamientos = new HashSet<Treatment>();
		
		if(this.hasDiagnostic(nombre, sistema)){
			for(MedicalHistory h: sistema.getMedicalHistories()){
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
	
	public boolean hasDiagnostic(String nombre, System sistema){
		for(Diagnostic d: sistema.getDiagnoses()){
			if(d.getName().toUpperCase().equals(nombre.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}
	
	
//	public List<Treatment> suggestTreatmentsToPatient(Patient paciente,String nombreDiagnostico, System sistema) throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
//		List<Treatment> tratamientos = this.diagnosticTreatments(nombreDiagnostico, sistema);
//		MedicalHistory historia = this.getMedicalHistoryFrom(paciente, sistema);
//		List<Treatment> tratamientosReturn = new ArrayList<Treatment>();
//		for(Treatment t: tratamientos){
//			if(historia.patientSupports(t)){
//				tratamientosReturn.add(t);
//			}
//		}
//		return tratamientosReturn;
//	}
	
	
	public List<Diagnostic> possibleDiagnosticForSymptomatology
	(List<Symptom> sintomatologia, System sistema){
		
		ArrayList<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		
		for(Diagnostic each : sistema.getDiagnoses()){
			if(each.getSymptoms().containsAll(sintomatologia)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
		
	}
	
	public List<Diagnostic> whoHadSypmtomAlsoHad
	(Symptom sintoma, System sistema){
		
		ArrayList<Diagnostic> diagnosticos = new ArrayList<Diagnostic>();
		
		for(Diagnostic each : sistema.getDiagnoses()){
			if(each.getSymptoms().contains(sintoma)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
	}
	
	public List<Diagnostic> whoHadADiseaseAlsoHad(Diagnostic diagnostico, System sistema){
		
		Set<Diagnostic> diagnosticos = new HashSet<Diagnostic>();
		
		for(MedicalHistory each : sistema.getMedicalHistories()){
			if(each.getDiagnostics().contains(diagnostico)){
				for(Diagnostic d : each.getDiagnostics()){
					diagnosticos.add(d);
				}
			}
		}
		
		ArrayList<Diagnostic> diagnosticosPosta = new ArrayList<Diagnostic>(diagnosticos);
		
		return diagnosticosPosta;
		
	}
	
	public void AddSymptomToDiagnostic(Symptom sintoma, Diagnostic diagnostico, System sistema){
		
		for(Diagnostic each :  sistema.getDiagnoses()){
			if(diagnostico.equals(each)){
				each.getSymptoms().add(sintoma);
			}
		}
		
	}
	
//	public void confirmDiagnosticForPatient(Patient paciente, Event evento, System sistema) throws PacienteNoEncontradoException{
//		
//		if(sistema.patientExists(paciente)){
//			this.getMedicalHistoryFrom(paciente, sistema).addEvent(evento);
//	
//		}else{
//			throw new PacienteNoEncontradoException();
//		}
//	}
}