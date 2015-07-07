package ar.edu.unq.desapp.groupb.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.repositories.DiagnosticDAO;

public class DiagnosticService extends GenericService<Diagnostic> {

	private static final long serialVersionUID = -6652628622081947349L;
	
	public Map<String,Float> percentageOfSymptomsFrom(DateTime date) {
		List<String> symptoms = ((DiagnosticDAO) this.getRepository()).symptoms(date);
		
		Map<String,Float> reporte = new HashMap<String,Float>();
		
		for(String nombre : symptoms){
			float frecuenciaDelSintoma = Collections.frequency(symptoms, nombre);
			reporte.put(nombre, (frecuenciaDelSintoma * 100) / symptoms.size());
		}
			
		return reporte;
	}

}
