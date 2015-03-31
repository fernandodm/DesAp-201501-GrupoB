package ar.edu.unq.desapp.grupob;

import java.util.List;

public class Diagnostico {
	
	private String nombre;
	private List<Sintoma> sintomas;
	private Tratamiento tratamiento;
	
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Sintoma> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	//////////////////////////////////////
	
}
