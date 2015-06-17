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

import ar.edu.unq.desapp.groupb.model.Professional;
import ar.edu.unq.desapp.groupb.services.ProfessionalService;

@Path("/professionals")
public class ProfessionalRest {
    
    private ProfessionalService professionalService;
    

    public ProfessionalService getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}

	@GET
    @Path("/count")
    @Produces("application/json")
    public Integer countProfessionalsAmount() {
        return professionalService.getRepository().count();
    }
    
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<Professional> getProfessionals() {
        List<Professional> professionals = professionalService.retriveAll();
        return professionals;
    }
    
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createProfessional(@FormParam("name") String name) {
    	Professional d = new Professional();
        getProfessionalService().save(d);
        return Response.ok(d).build();
    }
}
