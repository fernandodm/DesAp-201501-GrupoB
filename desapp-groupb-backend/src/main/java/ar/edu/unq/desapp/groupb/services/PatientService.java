package ar.edu.unq.desapp.groupb.services;

import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.repositories.PatientDAO;

public class PatientService extends GenericService<Patient> {

	private static final long serialVersionUID = 7810311595651598047L;

	public Patient findByDni(String dni) {
		return ((PatientDAO) this.getRepository()).findByDni(dni);
	}

}
