package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

public class Diagnostico {
	
	private String nombre;
	private List<Sintoma> sintomas = new ArrayList<Sintoma>();
	private List<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
	
	public Diagnostico(String nombre, List<Sintoma> sintomas, List<Tratamiento> tratamientos){
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.tratamientos = tratamientos;
	}
	
	public Diagnostico(String nombre){
		this.nombre = nombre;
	}
	
	public void agregarSintoma(Sintoma sintoma){
		this.sintomas.add(sintoma);
	}
	
	public void agregarTratamiento(Tratamiento tratamiento){
		this.tratamientos.add(tratamiento);
	}
	
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
	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}
	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}
	
	//////////////////////////////////////
	
}
