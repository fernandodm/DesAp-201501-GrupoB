package ar.edu.unq.desapp.groupb.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Event;

@SuppressWarnings("unchecked")
public class EventDAO extends HibernateGenericDAO<Event> implements GenericRepository<Event> {

	private static final long serialVersionUID = 1968331268680820542L;

	@Override
	protected Class<Event> getDomainClass() {
		return Event.class;
	}

	//obtengo los sintomas entre la fecha de hoy y dateToCompare
	public List<String> symptoms(DateTime dateToCompare) {
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		DateTime today = new DateTime();
        try {
        	 String queryStr = " SELECT e.diagnostic FROM " + this.persistentClass.getName() + " AS e WHERE e.date BETWEEN :dateToCompare AND :today";

             List<Diagnostic> diagnoses = session.createQuery(queryStr).setParameter("today", today)
             											       .setParameter("dateToCompare", dateToCompare).list();
             //obtener los sintomas
             List<String> symptoms = new ArrayList<String>();
     		 for(Diagnostic d: diagnoses){
     			symptoms.addAll(d.getSymptoms());
     		 }
     		 
             return symptoms;

        } finally {
            session.close();
        }
	}

}
