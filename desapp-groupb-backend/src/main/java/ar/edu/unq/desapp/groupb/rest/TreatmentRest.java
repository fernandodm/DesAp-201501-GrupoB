/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.Arrays;
import java.util.List;

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
import ar.edu.unq.desapp.groupb.services.TreatmentService;

@Path("/treatments")
public class TreatmentRest {
    
    private TreatmentService treatmentService;
    private DiagnosticService diagnosticService;



	public DiagnosticService getDiagnosticService() {
		return diagnosticService;
	}

	public void setDiagnosticService(DiagnosticService diagnosticService) {
		this.diagnosticService = diagnosticService;
	}

	public TreatmentService getTreatmentService() {
		return treatmentService;
	}

	public void setTreatmentService(TreatmentService treatmentService) {
		this.treatmentService = treatmentService;
	}

	@GET
    @Path("/count")
    @Produces("application/json")
    public Integer countTreatmentsAmount() {
        return treatmentService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Treatment> getTreaments() {
        List<Treatment> treatments = treatmentService.retriveAll();
        return treatments;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createTreatment(@FormParam("id") Integer id,@FormParam("id") Integer idd, @FormParam("repose") String repose,
    		@FormParam("type") String type, @FormParam("time") String time, @FormParam("medicalPractices") String medicalPractices,
    		@FormParam("medicines") String medicines)  {
    	Treatment treat = new Treatment();
    	List<String> medicalPracticesAsList = Arrays.asList(StringUtils.split(medicalPractices, ","));
    	Diagnostic diag = this.getDiagnosticService().findById(idd);
    	if(repose.equals("true")){
    		treat.setRepose(true);
    	} else {
    		treat.setRepose(false);
    	}
    	if(type.equals("true")){
    		treat.setType("Total");
    	} else {
    		treat.setType("Parcial");
    	}
    	
    	
    	treat.setTime(Integer.parseInt(time));
    	treat.setMedicalPractices(medicalPracticesAsList);
    	
    	diag.setTreatment(treat);
        getDiagnosticService().update(diag);
        return Response.ok(diag).build();
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
	@PUT
	@Path("/update/{idd}/{medicalPractice}")
	@Produces("application/json")
	public Response updateMedicalPractice(@PathParam("idd") final
			Integer idd,@PathParam("medicalPractice") final String medicalPractice) {
		
		Diagnostic diag = this.getDiagnosticService().findById(idd);
		diag.getTreatment().getMedicalPractices().add(medicalPractice);

		
		getDiagnosticService().update(diag);
		return Response.ok(diag).build();
	}
	
	
	@PUT
	@Path("/delete/{idd}/{medicalPractice}")
	@Produces("application/json")
	public Response deleteMedicalPractice(@PathParam("idd") final
			Integer idd,@PathParam("medicalPractice") final String medicalPractice) {
		
		Diagnostic diag = this.getDiagnosticService().findById(idd);
		diag.getTreatment().getMedicalPractices().remove(medicalPractice);
		getDiagnosticService().update(diag);
		
		return Response.ok(diag).build();
	}

    
//
//
//    public void setPostDAO(final PostRepository postDAO) {
//        this.postDAO = postDAO;
//    }
}
