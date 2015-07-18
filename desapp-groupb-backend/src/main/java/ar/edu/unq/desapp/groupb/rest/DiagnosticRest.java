/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Treatment;
import ar.edu.unq.desapp.groupb.services.DiagnosticService;
import ar.edu.unq.desapp.groupb.services.MedicalHistoryService;

@Path("/diagnoses")
public class DiagnosticRest {
    
    private DiagnosticService diagnosticService;
    private MedicalHistoryService medicalHistoryService;
    
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
    	Diagnostic diagnostic = new Diagnostic();
    	diagnostic.assignParameters(diagnostic, name, symptoms, date, new Treatment());
    	
    	MedicalHistory medical = getMedicalHistoryService().findById(id);
    	medical.getDiagnoses().add(diagnostic);       
		getMedicalHistoryService().update(medical);
		return diagnostic;
    }
    
    @PUT
    @Path("/update/{id}/{name}/{symptoms}/{day}/{month}/{year}")
    @Produces("application/json")
    public Diagnostic editDiagnostic(@PathParam("id") Integer id, @PathParam("name") String name,
    		@PathParam("symptoms") String symptoms, @PathParam("day") String day, @PathParam("month") String month, @PathParam("year") String year) {
    	Diagnostic diagnostic = this.getDiagnosticService().findById(id);
    	String date = day + "/" + month + "/" + year;
    	diagnostic.assignParameters(diagnostic, name, symptoms, date, diagnostic.getTreatment());;
    	 
		getDiagnosticService().update(diagnostic);
		return diagnostic;
    }
    
    
    
    @POST
    @Path("/assignDiagnostic/{id}/{name}/{symptoms}/{day}/{month}/{year}")
    @Produces("application/json")
    public Diagnostic assignDiagnostic(@PathParam("id") Integer id, @PathParam("name") String name,
    		@PathParam("symptoms") String symptoms, @PathParam("day") String day, @PathParam("month") String month, @PathParam("year") String year) {
    	Diagnostic diagnostic = new Diagnostic();
    	String date = day + "/" + month + "/" + year;
    	diagnostic.assignParameters(diagnostic, name, symptoms, date, new Treatment());
    	
    	MedicalHistory medical = getMedicalHistoryService().findById(id);
    	medical.getDiagnoses().add(diagnostic);       
		getMedicalHistoryService().update(medical);
		return diagnostic;
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
    
    @GET
    @Path("/symptoms/lastMonth")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsMonth() {
    	
    	DateTime dateLastMonth = new DateTime().minusMonths(1);
    	
        return getDiagnosticService().percentageOfSymptomsFrom(dateLastMonth);
        
    }
    
    @GET
    @Path("/symptoms/semester")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsSemester() {
    	
    	DateTime dateLastSemester = new DateTime().minusMonths(6);
    	
        return getDiagnosticService().percentageOfSymptomsFrom(dateLastSemester);
    }
    
    @GET
    @Path("/symptoms/year")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsYear() {
    	
    	DateTime dateLastYear = new DateTime().minusYears(1);
    	
        return getDiagnosticService().percentageOfSymptomsFrom(dateLastYear);
    }
    
    @GET
    @Path("/symptoms/list")
    @Produces("application/json")
    public Set<String> getSymptoms() {
        return getDiagnosticService().getSymptoms();
    }
    
	public MedicalHistoryService getMedicalHistoryService() {
		return medicalHistoryService;
	}

	public void setMedicalHistoryService(MedicalHistoryService medicalHistoryService) {
		this.medicalHistoryService = medicalHistoryService;
	}

}
