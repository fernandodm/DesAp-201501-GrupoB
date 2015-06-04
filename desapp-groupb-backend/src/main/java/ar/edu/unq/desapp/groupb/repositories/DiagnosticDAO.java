package ar.edu.unq.desapp.groupb.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
	
}
