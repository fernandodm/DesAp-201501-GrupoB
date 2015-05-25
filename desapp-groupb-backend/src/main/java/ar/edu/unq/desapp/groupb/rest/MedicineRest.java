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

import ar.edu.unq.desapp.groupb.model.Medicine;
import ar.edu.unq.desapp.groupb.services.MedicineService;

@Path("/medicines")
public class MedicineRest {
    
    private MedicineService medicineService;
    
    public MedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	@GET
    @Path("/count")
    @Produces("application/json")
    public Integer countMedicinesAmount() {
        return medicineService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Medicine> getMedicines() {
        List<Medicine> medicines = medicineService.retriveAll();
        return medicines;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createMedicine(@FormParam("name") String name) {
    	Medicine d = new Medicine();
        getMedicineService().save(d);
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
