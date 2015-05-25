package ar.edu.unq.desapp.groupb.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.groupb.model.Diagnostic;
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
		List<Patient> patients = patientService.retriveAll();
        return patients;
		
	}

	// @POST
	// @Path("/create/")
	// @Produces("application/json")
	// public Response createPatient(@FormParam("firstname") String firstname,
	// @FormParam("lastname") String lastname,
	// @FormParam("dni") String dni, @FormParam("username") String
	// username,@FormParam("password") String password){
	// getPatientDAO().savePatient(firstname,lastname,dni,username,password);
	// return Response.status(200).build();
	// }

	@POST
	@Path("/create")
	@Produces("application/json")
	public Response create(@FormParam("firstname") String name) {
		Patient d = new Patient();
		d.setFirstname(name);
		getPatientService().save(d);
		return Response.ok(d).build();
	}

	@GET
	@Path("/count")
	@Produces("application/json")
	public Integer countPatientsAmount() {
		return patientService.getRepository().count();
	}

	// @GET
	// @Path("/{from}")
	// @Produces("application/json")
	// public List<Post> findPostsPublishedByBlogId(@PathParam("from") final
	// Integer from) {
	// List<Post> posts = postDAO.getPosts(from, NUMBER_OF_POST, "");
	// return posts;
	// }

	// @GET
	// @Path("/byAuthor/{id}")
	// @Produces("application/json")
	// public List<Post> findPostsPublishedByAuthorId(@PathParam("id") final
	// String id) {
	// List<Post> posts = postDAO.getPosts(id);
	// return posts;
	// }
	//

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
