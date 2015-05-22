package ar.edu.unq.desapp.groupb.model;

import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;

public class Patient extends Person {

	private MedicalHistory medicalHistory;
	
	public Patient(){}
	
	
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}



	public void setMedicalHistory(MedicalHistory historiaClinica) {
		this.medicalHistory = historiaClinica;
	}



	public Patient(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
	}
	
	public void registrarEnElSistema(System sistema, String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
		sistema.registerNewPatientUser(nombre, apellido, dni, usuario, contrasena, peso, altura);
	}
}
