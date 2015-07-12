package ar.edu.unq.desapp.groupb.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Treatment;

public class DiagnosticDAO extends HibernateGenericDAO<Diagnostic> implements GenericRepository<Diagnostic> {

	private static final long serialVersionUID = 9143497671439570893L;

	@Override
	protected Class<Diagnostic> getDomainClass() {
		return Diagnostic.class;
	}

	public Diagnostic findByName(List<String> symptoms) {
        Criteria criteria = this.getSession().createCriteria(Diagnostic.class);
        criteria.add(Restrictions.ilike("symptoms", symptoms));
        return (Diagnostic) criteria.list();
    }

	public void saveDiagnostic(String name, List<String> symptoms, Treatment treatment) {
		Diagnostic d = new Diagnostic(name, symptoms, treatment);
		this.save(d);
		
	}
	
	//obtengo los sintomas entre la fecha de hoy y dateToCompare
		public List<String> symptoms(DateTime dateToCompare) {
			
			Session session = this.getHibernateTemplate().getSessionFactory().openSession();
			DateTime today = new DateTime();
	        try {
	        	 String queryStr = " SELECT d FROM " + this.persistentClass.getName() + " AS d WHERE d.date BETWEEN :dateToCompare AND :today";

	             @SuppressWarnings("unchecked")
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
	public Set<String> getAllSymptoms() {
			
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
	    try {
	      	 String queryStr = " SELECT d FROM " + this.persistentClass.getName() + " AS d";
             @SuppressWarnings("unchecked")
			 List<Diagnostic> diagnoses = session.createQuery(queryStr).list();
             //obtener los sintomas
             Set<String> symptoms = new HashSet<String>();
     		 for(Diagnostic d: diagnoses){
     			symptoms.addAll(d.getSymptoms());
    		 }
     		 
             return symptoms;

        } finally {
            session.close();
        }
	}

	
}
