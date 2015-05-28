package ar.edu.unq.desapp.groupb.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.groupb.model.MedicalHistory;
import ar.edu.unq.desapp.groupb.model.Patient;
import ar.edu.unq.desapp.groupb.services.PatientService;

@Path("/patients")
public class PatientRest {

	private PatientService patientService;

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Patient> getAllPatients() {
		List<Patient> patients = getPatientService().retriveAll();
        return patients;
		
	}

	@POST
	@Path("/create")
	@Produces("application/json")
	public Response createPatient(@FormParam("firstname") String firstname,
			 @FormParam("lastname") String lastname, @FormParam("dni") String dni, @FormParam("weight")Integer weight, @FormParam("height") Integer height){
		
		Patient p = new Patient(weight, height,firstname,lastname,dni,null,null);
		MedicalHistory m = new MedicalHistory();
		p.setMedicalHistory(m);
		getPatientService().save(p);
		return Response.ok(p).build();
	}

	@GET
	@Path("/count")
	@Produces("application/json")
	public Integer countPatientsAmount() {
		return getPatientService().count();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findById(@PathParam("id") final
			Integer id) {
		Patient p = getPatientService().findById(id);
		return Response.ok(p).build();
	}
	
	@PUT
	@Path("/update/{id}/{allergy}")
	@Produces("application/json")
	public Response updateAllery(@PathParam("id") final
			Integer id,@PathParam("allergy") final String allergy) {
		
		Patient p = getPatientService().findById(id);
		p.addAllergy(allergy);
		getPatientService().update(p);
		
		return Response.ok(p).build();
	}
	
	@PUT
	@Path("/delete/{id}/{allergy}")
	@Produces("application/json")
	public Response deleteAllergy(@PathParam("id") final
			Integer id,@PathParam("allergy") final String allergy) {
		
		Patient p = getPatientService().findById(id);
		p.deleteAllergy(allergy);
		getPatientService().update(p);
		
		return Response.ok(p).build();
	}

	// @GET
	// @Path("/{from}")
	// @Produces("application/json")
	// public List<Post> findPostsPublishedByBlogId(@PathParam("from") final
	// Integer from) {
	// List<Post> posts = postDAO.getPosts(from, NUMBER_OF_POST, "");
	// return posts;
	// }

	//
	// @GET
	// @Path("/tags")
	// @Produces("application/json")
	// public Set<String> getTagsByBlogId() {
	// return postDAO.getTags();
	// }
	//
	//
	//
	// public void setPostDAO(final PostRepository postDAO) {
	// this.postDAO = postDAO;
	// }

}
