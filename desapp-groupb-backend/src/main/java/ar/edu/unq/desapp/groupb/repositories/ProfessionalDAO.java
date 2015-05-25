package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Professional;

public class ProfessionalDAO extends HibernateGenericDAO<Professional> implements GenericRepository<Professional> {

	private static final long serialVersionUID = -1234737412969594251L;

	@Override
	protected Class<Professional> getDomainClass() {
		return Professional.class;
	}

}
