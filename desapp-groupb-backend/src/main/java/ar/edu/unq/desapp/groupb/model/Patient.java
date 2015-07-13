package ar.edu.unq.desapp.groupb.model;


public class Patient extends Entity {

	private String firstname;
	private String lastname;
	private String dni;
	private MedicalHistory medicalHistory;
	private Integer weight;
	private Integer height;
	
	public Patient(){}
	
	public Patient(Integer weight, Integer height,String nombre, String apellido, String dni){
		this.firstname = nombre;
		this.lastname = apellido;
		this.dni = dni;	
		this.weight = weight;
		this.height = height;
	}
	
	public Patient(String nombre, String apellido, String dni){
		this.setFirstname(nombre);
		this.setLastname(apellido);
		this.setDni(dni);
	}
	
	public void updatePatient(String nombre, String apellido, String dni, int height, int weight){
		this.setFirstname(nombre);
		this.setLastname(apellido);
		this.setDni(dni);
		this.setWeight(weight);
		this.setHeight(height);
		
	}
	
	public void deleteAllergy(String allergy) {
		this.getMedicalHistory().deleteAllergy(allergy);		
	}

	public void addAllergy(String allergy) {
		this.getMedicalHistory().addAllergy(allergy);		
	}
	
	public boolean isCompatibleWithTreatment(Treatment t){
		
		for(String st : this.getMedicalHistory().getAllergies()){
			for(String ss : t.medicineNames()){
				if(st.equals(ss)){
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	
	
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setMedicalHistory(MedicalHistory historiaClinica) {
		this.medicalHistory = historiaClinica;
	}	

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	//////////////////////////////
}
