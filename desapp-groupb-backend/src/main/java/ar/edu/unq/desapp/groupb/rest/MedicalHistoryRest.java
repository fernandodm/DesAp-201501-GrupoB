/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.services.MedicalHistoryService;

@Path("/medicalhistories")
public class MedicalHistoryRest {
    
    private MedicalHistoryService medicalHistoryService;
    

    public MedicalHistoryService getMedicalHistoryService() {
		return medicalHistoryService;
	}

	public void setMedicalHistoryService(MedicalHistoryService medicalHistoryService) {
		this.medicalHistoryService = medicalHistoryService;
	}

	@GET
    @Path("/count")
    @Produces("application/json")
    public Integer countMedicalHistoriesAmount() {
        return medicalHistoryService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<MedicalHistory> getMedicalHistories() {
        List<MedicalHistory> medicalHistories = medicalHistoryService.retriveAll();
        return medicalHistories;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createMedicalHistory(@FormParam("name") String name) {
    	MedicalHistory d = new MedicalHistory();
        getMedicalHistoryService().save(d);
        return Response.ok(d).build();
    }
    
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final
			Integer id) {
		MedicalHistory p = getMedicalHistoryService().findById(id);
		return Response.ok(p).build();
	}
	
	
	
	@GET
	@Path("/symptoms/lastMonth")
	@Produces("application/json")
	public Map<String,Float> percentageOfSymptomsMonth() {
		//obtengo todas las historias
		List<MedicalHistory> medicals = getMedicalHistoryService().retriveAll();
		
		//creo una fecha con dia del ante ultimo mes para comparar
		DateTime date = new DateTime().minusMonths(1);
		
		//obtengo todos los diagnosticos del ultimo mes
		List<Diagnostic> diagnoses =  diagnosesLastMonth(medicals, date);
		
		//obtengo todos los sintomas
		List<String> symptoms =  getSymptoms(diagnoses);
		
		
		Map<String,Float> reporte = new HashMap<String,Float>();
				
		for(String nombre : symptoms){
			float frecuenciaDelSintoma = Collections.frequency(symptoms, nombre);
			reporte.put(nombre, (frecuenciaDelSintoma * 100) / symptoms.size());
		}
			
		return reporte;
	}
	
	@GET
	@Path("/symptoms/semester")
	@Produces("application/json")
	public Map<String,Float> percentageOfSymptomsSemester() {
		//obtengo todas las historias
		List<MedicalHistory> medicals = getMedicalHistoryService().retriveAll();
		
		//creo una fecha con dia del ante ultimo mes para comparar
		DateTime date = new DateTime().minusMonths(6);
		
		//obtengo todos los diagnosticos del ultimo mes
		List<Diagnostic> diagnoses =  diagnosesLastMonth(medicals, date);
		
		//obtengo todos los sintomas
		List<String> symptoms =  getSymptoms(diagnoses);
		
		
		Map<String,Float> reporte = new HashMap<String,Float>();
				
		for(String nombre : symptoms){
			float frecuenciaDelSintoma = Collections.frequency(symptoms, nombre);
			reporte.put(nombre, (frecuenciaDelSintoma * 100) / symptoms.size());
		}
			
		return reporte;
	}
	
	@GET
	@Path("/symptoms/year")
	@Produces("application/json")
	public Map<String,Float> percentageOfSymptomsYear() {
		//obtengo todas las historias
		List<MedicalHistory> medicals = getMedicalHistoryService().retriveAll();
		
		//creo una fecha con dia del ante ultimo mes para comparar
		DateTime date = new DateTime().minusYears(1);
		
		//obtengo todos los diagnosticos del ultimo mes
		List<Diagnostic> diagnoses =  diagnosesLastMonth(medicals, date);
		
		//obtengo todos los sintomas
		List<String> symptoms =  getSymptoms(diagnoses);
		
		
		Map<String,Float> reporte = new HashMap<String,Float>();
				
		for(String nombre : symptoms){
			float frecuenciaDelSintoma = Collections.frequency(symptoms, nombre);
			reporte.put(nombre, (frecuenciaDelSintoma * 100) / symptoms.size());
		}
			
		return reporte;
	}

	private List<String> getSymptoms(List<Diagnostic> diagnoses) {
		
		List<String> symptoms = new ArrayList<String>();
		for(Diagnostic d: diagnoses){
			symptoms.addAll(d.getSymptoms());
		}
	
		return symptoms;
	}

	private List<Diagnostic> diagnosesLastMonth(List<MedicalHistory> medicals, DateTime date) {
		
		List<Diagnostic> diagnoses = new ArrayList<Diagnostic>();
		
		for(MedicalHistory mh: medicals){
			List<Event> events = mh.getEvents();
			for(Event e: events){
				if(date.isBefore(e.getDate())){
					diagnoses.add(e.getDiagnostic());
				}
			}
		}
		return diagnoses;
	}
	

}
