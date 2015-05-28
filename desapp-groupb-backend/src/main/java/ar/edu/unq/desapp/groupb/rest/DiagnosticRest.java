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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Symptom;
import ar.edu.unq.desapp.groupb.services.DiagnosticService;

@Path("/diagnoses")
public class DiagnosticRest {
    
    private DiagnosticService diagnosticService;
    
    

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
    public Response createDiagnostic(@FormParam("name") String name) {
    	Diagnostic d = new Diagnostic(name);
        getDiagnosticService().save(d);
        return Response.ok(d).build();
    }
    
    @GET
    @Path("/{symptoms}")
    @Produces("application/json")
    public List<Diagnostic> diagnosesContains(@PathParam("id") final List<String> symptoms) {
        List<Diagnostic> diagnoses = getDiagnosticService().retriveAll();
        List<Diagnostic> diagnosesWithSymptoms = new ArrayList<Diagnostic>();
        for(Diagnostic d: diagnoses){
        	for(Symptom s : d.getSymptoms()){
        		if(symptoms.contains(s.getSymptomName()))
        			diagnosesWithSymptoms.add(d);
        	}
        }
        
        return diagnosesWithSymptoms;
    }
    
    @POST
    @Path("/create/{patient}/{name}/{symptoms}")
    @Produces("application/json")
    public Response createDiagnosticWith(@FormParam("patient") Patient patient, 
    		@FormParam("name") String name, @FormParam("symptoms") List<String> symptoms) {
    	//PRUEBA--------------------------------
//    	Diagnostic d = new Diagnostic(name);
//    	List<Symptom> s = Arrays.asList(new Symptom("fiebre"),new Symptom("dolor"));
//    	d.setSymptoms(s);
//    	patient.getMedicalHistory().addEvent(new Event(null, d));
//        getDiagnosticService().save(patient);
//        return Response.ok(d).build();
    	return null;
    }
    
//  @GET
//  @Path("/{from}")
//  @Produces("application/json")
//  public List<Post> findPostsPublishedByBlogId(@PathParam("from") final Integer from) {
//      List<Post> posts = postDAO.getPosts(from, NUMBER_OF_POST, "");
//      return posts;
//  }

//  @GET
//  @Path("/{id}")
//  @Produces("application/json")
//  public List<Post> findPostsPublishedByAuthorId(@PathParam("id") final String id) {
//      List<Post> posts = postDAO.getPosts(id);
//      return posts;
//  }

//
//    @GET
//    @Path("/tags")
//    @Produces("application/json")
//    public Set<String> getTagsByBlogId() {
//        return postDAO.getTags();
//    }
//
//
//
//    public void setPostDAO(final PostRepository postDAO) {
//        this.postDAO = postDAO;
//    }
}
