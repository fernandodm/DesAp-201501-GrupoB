package ar.edu.unq.desapp.grupob;

import java.util.Calendar;
import java.util.List;

public class HistoriaClinica {
	
	private int peso;
	private int altura;
	private List<String> alergias;
	private Calendar fecha;
	private List<Diagnostico> diagnostico;
	
	
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
	
	///////////////////////////////////////////////////////

}
