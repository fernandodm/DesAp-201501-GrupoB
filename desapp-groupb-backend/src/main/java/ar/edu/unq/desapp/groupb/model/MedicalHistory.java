package ar.edu.unq.desapp.groupb.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistory extends Entity {
	
	private List<String> allergies = new ArrayList<String>();
	private List<Diagnostic> diagnoses = new ArrayList<Diagnostic>();
		
	public MedicalHistory(){}
	
	
	/* ACLARACION: se podria poner como precondicion que la primer letra sea 
	 * mayuscula y el resto minuscula o pasar todo a mayuscula y comparar
	 * ahora voy hacer la primera opcion 
	 * */
	public boolean isAllergicTo(String alergia){
		return this.getAllergies().contains(alergia);
	}
	
	public void deleteAllergy(String alergia){
		this.getAllergies().remove(alergia);
	}
	
	public void addAllergy(String alergia){
		this.getAllergies().add(alergia);
	}
	
	public boolean patientSupports(Treatment t) {
		List<Medicine> medicamentos = t.getMedicines();
		for(Medicine m: medicamentos){
			if(this.getAllergies().contains(m.getDrugName())){
				return true;
			}
		}
		return false;
	}

	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> alergies) {
		this.allergies = alergies;
	}


	public List<Diagnostic> getDiagnoses() {
		return diagnoses;
	}


	public void setDiagnoses(List<Diagnostic> diagnoses) {
		this.diagnoses = diagnoses;
	}

	
	//////////////////////////////
}