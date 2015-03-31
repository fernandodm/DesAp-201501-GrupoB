package ar.edu.unq.desapp.grupob;

import java.util.List;

public class Tratamiento {
	
	private boolean reposo;
	private String tipo;
	private int tiempo;
	private List<String> practicasMedicas;
	private List<Medicamento> medicamentos;
	
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
