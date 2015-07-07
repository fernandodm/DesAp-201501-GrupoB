package ar.edu.unq.desapp.groupb.services;

public class GeneralService {

	private DiagnosticService diagnosticService;
	private PatientService patientService;
	private MedicalHistoryService medicalHistoryService;
	private MedicineService medicineService;
	private ProfessionalService professionalService;
	private TreatmentService treatmentService;

	public MedicalHistoryService getMedicalHistoryService() {
		return medicalHistoryService;
	}

	public void setMedicalHistoryService(MedicalHistoryService medicalHistoryService) {
		this.medicalHistoryService = medicalHistoryService;
	}

	public MedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	public ProfessionalService getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}

	public TreatmentService getTreatmentService() {
		return treatmentService;
	}

	public void setTreatmentService(TreatmentService treatmentService) {
		this.treatmentService = treatmentService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public DiagnosticService getDiagnosticService() {
		return diagnosticService;
	}

	public void setDiagnosticService(DiagnosticService diagnosticService) {
		this.diagnosticService = diagnosticService;
	}

}
