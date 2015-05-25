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

import ar.edu.unq.desapp.groupb.model.Event;
import ar.edu.unq.desapp.groupb.services.EventService;

@Path("/events")
public class EventRest {
    
    private EventService eventService;

    public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@GET
    @Path("/count")
    @Produces("application/json")
    public Integer countEventsAmount() {
        return eventService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Event> getEvents() {
        List<Event> events = eventService.retriveAll();
        return events;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createEvent(@FormParam("name") String name) {
    	Event d = new Event();
        getEventService().save(d);
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
