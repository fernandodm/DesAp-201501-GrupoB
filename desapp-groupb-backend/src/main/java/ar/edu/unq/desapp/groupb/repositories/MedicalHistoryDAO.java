package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.MedicalHistory;

public class MedicalHistoryDAO extends HibernateGenericDAO<MedicalHistory> implements GenericRepository<MedicalHistory> {

	private static final long serialVersionUID = 7561504669273427486L;

	@Override
	protected Class<MedicalHistory> getDomainClass() {
		return MedicalHistory.class;
	}

}
