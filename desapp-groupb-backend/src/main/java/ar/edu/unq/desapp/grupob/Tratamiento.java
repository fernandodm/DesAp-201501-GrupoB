package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.List;

public class Tratamiento {
	
	private boolean reposo;
	private String tipo;
	private int tiempo;
	private List<String> practicasMedicas = new ArrayList<String>();
	private List<Medicamento> medicamentos = new ArrayList<Medicamento>();
	
	public Tratamiento(boolean reposo, String tipo, int tiempo){
		this.reposo = reposo;
		this.tipo = tipo;
		this.tiempo = tiempo;
	}
	
	public void agregarMedicamento(String droga, int concentracion, int tiempo){
		Medicamento medicamento = new Medicamento(droga, concentracion, tiempo);
		this.medicamentos.add(medicamento);
	}
	
	public void agregarPracticaMedica(String practicaMedica){
		this.getPracticasMedicas().add(practicaMedica);		
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public boolean isReposo() {
		return reposo;
	}
	public void setReposo(boolean reposo) {
		this.reposo = reposo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public List<String> getPracticasMedicas() {
		return practicasMedicas;
	}
	public void setPracticasMedicas(List<String> practicasMedicas) {
		this.practicasMedicas = practicasMedicas;
	}
	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	////////////////////////////////////////
}
