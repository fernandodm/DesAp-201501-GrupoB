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

import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.model.Treatment;
import ar.edu.unq.desapp.groupb.services.TreatmentService;

@Path("/treatments")
public class TreatmentRest {
    
    private TreatmentService treatmentService;


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
    public Response createTreatment(@FormParam("id") Integer id, @FormParam("repose") String repose,
    		@FormParam("type") String type, @FormParam("time") String time, @FormParam("medicalPractices") String medicalPractices,
    		@FormParam("medicines") String medicines)  {
    	Treatment d = new Treatment();
    	if(repose.equals("true")){
    		d.setRepose(true);
    	} else {
    		d.setRepose(false);
    	}
    	List<String> medicalPracticesAsList = Arrays.asList(StringUtils.split(medicalPractices, ","));

    	d.setType(type);
    	d.setTime(Integer.parseInt(time));
    	d.setMedicalPractices(medicalPracticesAsList);
    	
        getTreatmentService().save(d);
        return Response.ok(d).build();
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
	@Path("/update/{id}/{medicalPractice}")
	@Produces("application/json")
	public Response updateMedicalPractice(@PathParam("id") final
			Integer id,@PathParam("medicalPractice") final String medicalPractice) {
		
		Treatment t = getTreatmentService().findById(id);
		t.getMedicalPractices().add(medicalPractice);
		getTreatmentService().update(t);
		
		return Response.ok(t).build();
	}
	
	
	@PUT
	@Path("/delete/{id}/{medicalPractice}")
	@Produces("application/json")
	public Response deleteMedicalPractice(@PathParam("id") final
			Integer id,@PathParam("medicalPractice") final String medicalPractice) {
		
		Treatment t = getTreatmentService().findById(id);
		t.getMedicalPractices().remove(medicalPractice);
		getTreatmentService().update(t);
		
		return Response.ok(t).build();
	}

    
//
//
//    public void setPostDAO(final PostRepository postDAO) {
//        this.postDAO = postDAO;
//    }
}
