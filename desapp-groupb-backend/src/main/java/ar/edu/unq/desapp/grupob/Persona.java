package ar.edu.unq.desapp.grupob;

import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;

public class Persona {

	private String nombre;
	private String apellido;
	private String dni;
	private String usuario;
	private String contrasena;
	
	public Persona(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuario = usuario;
		this.contrasena = contrasena;
		
	}
	
	////////////////////////
	//GETTERS AND SETTERS//
	////////////////////////
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public void login(Sistema sistema, String usuario, String password) throws PacienteNoEncontradoException{
		if(sistema.loginValido(usuario, password)){
			System.out.println("Login satisfactorio!");
		}
	}
	
	/////////////////////////////////////

}
