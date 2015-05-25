package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Patient;

public class PatientDAO extends HibernateGenericDAO<Patient> implements GenericRepository<Patient> {

	private static final long serialVersionUID = -3252599980258592660L;

	@Override
	protected Class getDomainClass() {
		// TODO Auto-generated method stub
		return Patient.class;
	}

	public void savePatient(String firstname, String lastname, String dni,
			String username, String password) {
		Patient p = new Patient(firstname,lastname,dni,username,password);
		this.save(p);
	}

}
