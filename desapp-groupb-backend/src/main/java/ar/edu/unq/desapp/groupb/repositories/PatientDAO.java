package ar.edu.unq.desapp.groupb.repositories;

import java.util.List;

import org.hibernate.Session;

import ar.edu.unq.desapp.groupb.model.Patient;

public class PatientDAO extends HibernateGenericDAO<Patient> implements GenericRepository<Patient> {

	private static final long serialVersionUID = -3252599980258592660L;

	@Override
	protected Class<Patient> getDomainClass() {
		return Patient.class;
	}
	
	public Patient findByDni(String dni) {
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        try {
        	 String queryStr = " SELECT e FROM " + this.persistentClass.getName() + " AS e WHERE e.dni like :dni";

             @SuppressWarnings("unchecked")
			List<Patient> patient = session.createQuery(queryStr).setParameter("dni", dni).list();
             
             if(patient.size() == 0){
            	 return null;
             }else{
            	 return patient.get(0);
             }

        } finally {
            session.close();
        }
	}
}
