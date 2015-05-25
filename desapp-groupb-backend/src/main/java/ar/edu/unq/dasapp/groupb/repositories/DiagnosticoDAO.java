package ar.edu.unq.dasapp.groupb.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.groupb.model.Diagnostic;

@SuppressWarnings("all")
public class DiagnosticoDAO extends HibernateGenericDAO {

	@Override
	protected Class<Diagnostic> getDomainClass() {
		return Diagnostic.class;
	}

	public Diagnostic findByName(List<String> symptoms) {
        Criteria criteria = this.getSession().createCriteria(Diagnostic.class);
        criteria.add(Restrictions.ilike("symptoms", symptoms));
        return (Diagnostic) criteria.list();
    }
	
}
