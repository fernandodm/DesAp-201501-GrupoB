/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
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

    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Diagnostic createDiagnostic(@FormParam("id") Integer id, @FormParam("name") String name,
    		@FormParam("symptoms") String symptoms, @FormParam("date") String date) {
    	List<String> symptomsAsList = Arrays.asList(StringUtils.split(symptoms, ","));
    	Diagnostic diagnostic = new Diagnostic(name,symptomsAsList,new Treatment());
    	
    	diagnostic.setDate(diagnostic.stringToDateTime(date));
    	
    	MedicalHistory medical = getMedicalHistoryService().findById(id);
    	medical.getDiagnoses().add(diagnostic);       
		getMedicalHistoryService().update(medical);
		return diagnostic;
    }
    
    @POST
    @Path("/assignDiagnostic/{id}/{name}/{symptoms}/{day}/{month}/{year}")
    @Produces("application/json")
    public Diagnostic assignDiagnostic(@PathParam("id") Integer id, @PathParam("name") String name,
    		@PathParam("symptoms") String symptoms, @PathParam("day") String day, @PathParam("month") String month, @PathParam("year") String year) {
    	List<String> symptomsAsList = Arrays.asList(StringUtils.split(symptoms, ","));
    	Diagnostic diagnostic = new Diagnostic(name,symptomsAsList,new Treatment());
    	String date = day + "/" + month + "/" + year;
    	diagnostic.setDate(diagnostic.stringToDateTime(date));
    	diagnostic.setName(name);
    	
    	MedicalHistory medical = getMedicalHistoryService().findById(id);
    	medical.getDiagnoses().add(diagnostic);       
		getMedicalHistoryService().update(medical);
		return diagnostic;
    }
    
//    @DELETE
//    @Path("/delete/{id}")
//    @Produces("application/json")
//    public void deleteDiagnoses(@PathParam("id") Integer id) {
//        Diagnostic diagnostic = getDiagnosticService().findById(id);
//        
//        getDiagnosticService().delete(diagnostic);
// 
//    }
    
    @POST
    @Path("/delete/{id}/{idd}")
    @Produces("application/json")
    public void deleteDiagnoses(@PathParam("id") Integer id,@PathParam("idd") Integer idd) {
        Diagnostic diagnostic = getDiagnosticService().findById(idd);
        diagnostic.eraseAll();
        this.getDiagnosticService().update(diagnostic);
        MedicalHistory m = this.getMedicalHistoryService().findById(id);
//        Integer n = m.getDiagnoses().indexOf(diagnostic);
//        m.getDiagnoses().remove(diagnostic);
        List<Diagnostic> diags = m.getDiagnoses();
        for(Diagnostic c : diags){
        	if(c.getId() == idd){
        		diags.remove(c);
        	}
        }
//        diags.remove(diagnostic);
        List<Diagnostic> diags2 = new ArrayList<Diagnostic>();
        for(Diagnostic each : diags){
        	diags2.add(each);
        }
        m.setDiagnoses(diags2);
        
//        this.getDiagnosticService().delete(diagnostic);
        this.getMedicalHistoryService().update(m);
//        getDiagnosticService().delete(diagnostic);
 
    }
    
    
    @GET
    @Path("/diagnostic/{id}")
    @Produces("application/json")
    public Diagnostic findById(@PathParam("id") Integer id) {
        Diagnostic diagnostic = getDiagnosticService().findById(id);
        
        return diagnostic;
 
    }
    
    @GET
    @Path("/{symptoms}")
    @Produces("application/json")
    public List<Diagnostic> diagnosesContains(@PathParam("symptoms") final String symptoms) {
        List<Diagnostic> diagnoses = getDiagnosticService().retriveAll();
        List<Diagnostic> diagnosesWithSymptoms = new ArrayList<Diagnostic>();

        for(Diagnostic d: diagnoses){
        		if(d.getSymptoms().containsAll(Arrays.asList(StringUtils.split(symptoms, ","))))
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
