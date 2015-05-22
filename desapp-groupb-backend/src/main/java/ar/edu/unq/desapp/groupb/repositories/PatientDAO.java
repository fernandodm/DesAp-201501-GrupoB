package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Patient;

public class PatientDAO extends HibernateGenericDAO<Patient> implements GenericRepository<Patient> {

	private static final long serialVersionUID = -3252599980258592660L;

	@Override
	protected Class getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
