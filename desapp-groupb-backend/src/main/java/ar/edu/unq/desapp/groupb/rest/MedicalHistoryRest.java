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
