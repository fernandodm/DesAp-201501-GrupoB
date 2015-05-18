package ar.edu.unq.desapp.groupb.model;

import ar.edu.unq.desapp.groupb.model.exceptions.NombreDeUsuarioYaTomado;

public class Patient extends Person {

	private MedicalHistory historiaClinica;
	
	public Patient(){}
	
	
	public MedicalHistory getHistoriaClinica() {
		return historiaClinica;
	}



	public void setHistoriaClinica(MedicalHistory historiaClinica) {
		this.historiaClinica = historiaClinica;
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
