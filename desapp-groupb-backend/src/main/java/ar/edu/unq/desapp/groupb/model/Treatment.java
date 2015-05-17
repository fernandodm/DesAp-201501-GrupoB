package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class Treatment extends Entity{
	
	private boolean repose;
	private String type;
	private int time;
	private List<String> medicalPractices = new ArrayList<String>();
	private List<Medicamento> medicines = new ArrayList<Medicamento>();
	
	public Treatment(){}
	
	public Treatment(boolean reposo, String tipo, int tiempo){
		this.repose = reposo;
		this.type = tipo;
		this.time = tiempo;
	}
	
	public void agregarMedicamento(String droga, int concentracion, int tiempo){
		Medicamento medicamento = new Medicamento(droga, concentracion, tiempo);
		this.medicines.add(medicamento);
	}
	
	public void agregarPracticaMedica(String practicaMedica){
		this.getMedicalPractices().add(practicaMedica);		
	}

////////////////////////
// GETTERS AND SETTERS//
////////////////////////
	
	public boolean isRepose() {
		return repose;
	}

	public void setRepose(boolean repose) {
		this.repose = repose;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<String> getMedicalPractices() {
		return medicalPractices;
	}

	public void setMedicalPractices(List<String> medicalPractices) {
		this.medicalPractices = medicalPractices;
	}

	public List<Medicamento> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicamento> medicines) {
		this.medicines = medicines;
	}
	////////////////////////////////////////
}
