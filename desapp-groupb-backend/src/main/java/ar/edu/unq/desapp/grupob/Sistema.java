package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

	private List<Persona> usuarios = new ArrayList<Persona>();
	private List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
	private List<HistoriaClinica> historias = new ArrayList<HistoriaClinica>();
	
	public List<Persona> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Persona> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<HistoriaClinica> getHistorias() {
		return historias;
	}
	public void setHistorias(List<HistoriaClinica> historias) {
		this.historias = historias;
	}
	
	public void registrarUsuario(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		
		Persona persona = new Persona(nombre, apellido, dni, usuario, contrasena);
		this.usuarios.add(persona);
	}
	
	public boolean existePaciente(Paciente paciente){
		return this.getUsuarios().contains(paciente);
	}
	
	
}
