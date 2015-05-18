package ar.edu.unq.desapp.groupb.model;

import ar.edu.unq.desapp.groupb.model.exceptions.PacienteNoEncontradoException;

public class Person extends Entity {

	private String firstname;
	private String lastname;
	private String dni;
	private String username;
	private String password;
	
	public Person(){}
	
	public Person(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		this.firstname = nombre;
		this.lastname = apellido;
		this.dni = dni;
		this.username = usuario;
		this.password = contrasena;
		
	}
	
	////////////////////////
	//GETTERS AND SETTERS//
	////////////////////////

	

	public String getDni() {
		return dni;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
	public void login(System sistema, String usuario, String password) throws PacienteNoEncontradoException{
		if(sistema.loginSuccess(usuario, password)){
			java.lang.System.out.println("Login satisfactorio!");
			
		}
	}
	
	/////////////////////////////////////

}
