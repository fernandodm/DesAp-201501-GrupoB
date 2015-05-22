package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Treatment;

public class TreatmentDAO extends HibernateGenericDAO<Treatment> implements GenericRepository<Treatment> {

	private static final long serialVersionUID = -6580172115536493048L;

	@Override
	protected Class getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
