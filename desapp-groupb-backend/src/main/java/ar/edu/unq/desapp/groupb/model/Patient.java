package ar.edu.unq.desapp.groupb.model;


public class Patient extends Person {

	private MedicalHistory medicalHistory;
	private Integer weight;
	private Integer height;
	
	public Patient(){}
	
	public Patient(Integer weight, Integer height,String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		this.weight = weight;
		this.height = height;
//		MedicalHistory medicalHistory = new MedicalHistory(this, weight, height);
//		this.setMedicalHistory(medicalHistory);
	}
	
	
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}



	public void setMedicalHistory(MedicalHistory historiaClinica) {
		this.medicalHistory = historiaClinica;
	}



	public Patient(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
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
	
//	public void registrarEnElSistema(System sistema, String nombre, String apellido, String dni,
//			String usuario, String contrasena, int peso, int altura) throws NombreDeUsuarioYaTomado{
//		sistema.registerNewPatientUser(nombre, apellido, dni, usuario, contrasena, peso, altura);
//	}
}
