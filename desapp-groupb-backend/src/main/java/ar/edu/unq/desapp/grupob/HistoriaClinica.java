package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoriaClinica {
	
	private int peso;
	private int altura;
	private List<String> alergias = new ArrayList<String>();
	private Calendar fecha;
	private List<Diagnostico> diagnostico = new ArrayList<Diagnostico>();
	private Persona persona;
	
	
	public HistoriaClinica(int peso, int altura, Calendar fecha, Persona persona){
		this.peso = peso;
		this.altura = altura;
		this.fecha = fecha;
		this.setPersona(persona);
	}
	
	public void agregarAlergia(String alergia){
		this.alergias.add(alergia);		
	}
	
	public void agregarDiagnostico(Diagnostico diagnostico){
		this.diagnostico.add(diagnostico);
	}
	
	/* ACLARACION: se podria poner como precondicion que la primer letra sea 
	 * mayuscula y el resto minuscula o pasar todo a mayuscula y comparar
	 * ahora voy hacer la primera opcion 
	 * */
	public boolean esAlergicoA(String alergia){
		return this.getAlergias().contains(alergia);
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public List<String> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<String> alergias) {
		this.alergias = alergias;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public List<Diagnostico> getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(List<Diagnostico> diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	///////////////////////////////////////////////////////

}
