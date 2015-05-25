package ar.edu.unq.desapp.groupb.repositories;

import ar.edu.unq.desapp.groupb.model.Event;

public class EventDAO extends HibernateGenericDAO<Event> implements GenericRepository<Event> {

	private static final long serialVersionUID = 1968331268680820542L;

	@Override
	protected Class<Event> getDomainClass() {
		return Event.class;
	}

}
