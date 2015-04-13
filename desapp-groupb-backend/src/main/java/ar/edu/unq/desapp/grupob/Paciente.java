package ar.edu.unq.desapp.grupob;

import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;

public class Paciente extends Persona {

	private HistoriaClinica historiaClinica;
	
	
	
	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}



	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}



	public Paciente(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
	}
	
	public void registrarEnElSistema(Sistema sistema, String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
		sistema.darDeAltaNuevoUsuarioPaciente(nombre, apellido, dni, usuario, contrasena, peso, altura);
	}
}
