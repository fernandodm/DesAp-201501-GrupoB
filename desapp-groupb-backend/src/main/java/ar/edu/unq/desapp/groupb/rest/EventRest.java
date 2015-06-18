/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

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
	public Response createEvent() {
	    Event e = new Event();
	    getEventService().save(e);
	    return Response.ok(e).build();
	}
	
    @GET
    @Path("/symptoms/lastMonth")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsMonth() {
    	
    	DateTime dateLastMonth = new DateTime().minusMonths(1);
    	
        return getEventService().percentageOfSymptomsFrom(dateLastMonth);
        
    }
    
    @GET
    @Path("/symptoms/semester")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsSemester() {
    	
    	DateTime dateLastSemester = new DateTime().minusMonths(6);
    	
        return getEventService().percentageOfSymptomsFrom(dateLastSemester);
    }
    
    @GET
    @Path("/symptoms/year")
    @Produces("application/json")
    public Map<String,Float> percentageOfSymptomsYear() {
    	
    	DateTime dateLastYear = new DateTime().minusYears(1);
    	
        return getEventService().percentageOfSymptomsFrom(dateLastYear);
    }
}
