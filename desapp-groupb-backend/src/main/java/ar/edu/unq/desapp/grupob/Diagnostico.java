package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

public class Diagnostico {
	
	private String nombre;
	private List<Sintoma> sintomas = new ArrayList<Sintoma>();
	private Tratamiento tratamiento;
	
	public Diagnostico(String nombre, List<Sintoma> sintomas, Tratamiento tratamiento){
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.tratamiento = tratamiento;
	}
	
	public Diagnostico(String nombre){
		this.nombre = nombre;
	}
	
	public void agregarSintoma(Sintoma sintoma){
		this.sintomas.add(sintoma);
	}
	
	public boolean seRelacionConElSintoma(Sintoma sintoma){
		return this.getSintomas().contains(sintoma);
	}
	
	public void eliminarSintoma(Sintoma sintoma){
		this.getSintomas().remove(sintoma);
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
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public List<String> listaSintomas(){
		
		List<String> ls = new ArrayList<String>();
		
		for(Sintoma each : this.getSintomas()){
			ls.add(each.getNombre());
		}
		
		return ls;
		
	}
	//////////////////////////////////////
	
}
