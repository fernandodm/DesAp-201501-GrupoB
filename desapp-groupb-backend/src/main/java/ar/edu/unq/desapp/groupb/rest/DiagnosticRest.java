/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Treatment;
import ar.edu.unq.desapp.groupb.services.DiagnosticService;
import ar.edu.unq.desapp.groupb.services.EventService;
import ar.edu.unq.desapp.groupb.services.MedicalHistoryService;

@Path("/diagnoses")
public class DiagnosticRest {
    
    private DiagnosticService diagnosticService;
    private MedicalHistoryService medicalHistoryService;
    private EventService eventService;
    
	public DiagnosticService getDiagnosticService() {
		return diagnosticService;
	}

	public void setDiagnosticService(DiagnosticService diagnosticService) {
		this.diagnosticService = diagnosticService;
	}

    @GET
    @Path("/count")
    @Produces("application/json")
    public Integer countDiagnosesAmount() {
        return diagnosticService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Diagnostic> getDiagnoses() {
        List<Diagnostic> diagnoses = diagnosticService.retriveAll();
        return diagnoses;
    }
    
//    @POST
//    @Path("/create")
//    @Produces("application/json")
//    public Response createDiagnostic() {
//    	Diagnostic d = new Diagnostic();
//        getDiagnosticService().save(d);
//        return Response.ok(d).build();
//    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Diagnostic createDiagnostic(@FormParam("id") Integer id, @FormParam("name") String name,
    		@FormParam("symptoms") String symptoms, @FormParam("date") String date) {
    	List<String> symptomsAsList = Arrays.asList(StringUtils.split(symptoms, ","));
    	Diagnostic diagnostic = new Diagnostic(name,symptomsAsList,new Treatment());
    	
    	MedicalHistory medical = getMedicalHistoryService().findById(id);
    	Event event = new Event(diagnostic);
    	event.setDate(event.stringToCalendar(date));
    	medical.addEvent(event);       
		getMedicalHistoryService().update(medical);
		return diagnostic;
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response deleteDiagnoses(@PathParam("id") Integer id) {
        Diagnostic diagnostic = getDiagnosticService().findById(id);
        getDiagnosticService().delete(diagnostic);
 
		return Response.ok(diagnostic).build();
    }
    
    @GET
    @Path("/{symptoms}")
    @Produces("application/json")
    public List<Diagnostic> diagnosesContains(@PathParam("symptoms") final String symptoms) {
        List<Diagnostic> diagnoses = getDiagnosticService().retriveAll();
        List<Diagnostic> diagnosesWithSymptoms = new ArrayList<Diagnostic>();

        for(Diagnostic d: diagnoses){
        		if(d.getSymptomsNames().containsAll(Arrays.asList(StringUtils.split(symptoms, ","))))
        			diagnosesWithSymptoms.add(d);
        }
        
        return diagnosesWithSymptoms;
    }
    
	public MedicalHistoryService getMedicalHistoryService() {
		return medicalHistoryService;
	}

	public void setMedicalHistoryService(MedicalHistoryService medicalHistoryService) {
		this.medicalHistoryService = medicalHistoryService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
