/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.groupb.model.Symptom;
import ar.edu.unq.desapp.groupb.services.SymptomService;

@Path("/symptoms")
public class SymptomRest {
    
    private SymptomService symptomService;

    
    @GET
    @Path("/count")
    @Produces("application/json")
    public Integer countDiagnosesAmount() {
        return symptomService.getRepository().count();
    }
    
	public SymptomService getSymptomService() {
		return symptomService;
	}

	public void setSymptomService(SymptomService symptomService) {
		this.symptomService = symptomService;
	}

	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Symptom> getSymptoms() {
        List<Symptom> symptoms = symptomService.retriveAll();
        return symptoms;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createSymptom(@FormParam("name") String name) {
    	Symptom d = new Symptom();
        getSymptomService().save(d);
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
//
//
//    public void setPostDAO(final PostRepository postDAO) {
//        this.postDAO = postDAO;
//    }
}
