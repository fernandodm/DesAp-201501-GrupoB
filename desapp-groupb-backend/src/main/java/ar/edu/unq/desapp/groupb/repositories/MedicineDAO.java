package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Medicine;

public class MedicineDAO extends HibernateGenericDAO<Medicine> implements GenericRepository<Medicine> {

	private static final long serialVersionUID = -6896254684800478575L;

	@Override
	protected Class getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
