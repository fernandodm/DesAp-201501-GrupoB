package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupob.excepciones.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;

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
	
	public boolean hayHistoriaClinicaConPaciente(Paciente paciente){
		boolean hay = false;
		for(HistoriaClinica each : this.getHistorias()){
			if(each.getPersona().equals(paciente)){
				hay = true;
				break;
			}
		}
		return hay;
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
	
	public void darDeAltaNuevoUsuarioProfesional(String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		
		if(!this.hayUsuarioConNombre(usuario)){
			Profesional profesional = new Profesional(nombre, apellido, dni, usuario, contrasena);
			this.usuarios.add(profesional);
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
		
	}
	
	public void darDeAltaNuevoUsuarioPaciente(String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
		if(!this.hayUsuarioConNombre(usuario)){
			Paciente paciente = new Paciente(nombre, apellido, dni, usuario, contrasena);
			this.usuarios.add(paciente);
			this.getHistorias().add(new HistoriaClinica(peso,altura,paciente));
		} else {
			throw new NombreDeUsuarioYaTomado();
		}
		
			
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
	
}
