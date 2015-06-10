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
import ar.edu.unq.desapp.groupb.model.Medicine;
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
    @Path("/create/{id}/{repose}/{type}/{time}/{medicalPractices}/{medicines}")
    @Produces("application/json")
    public Response createTreatment(@PathParam("id") Integer id, @PathParam("repose") String repose,
    		@PathParam("type") String type, @PathParam("time") Integer time, @PathParam("medicalPractices") String medicalPractices,
    		@PathParam("medicines") String medicines)  {
    	List<String> medicalPracticesAsList = Arrays.asList(StringUtils.split(medicalPractices, ","));
    	Diagnostic diag = this.getDiagnosticService().findById(id);
    	if(repose.equals("true")){
    		diag.getTreatment().setRepose(true);
    	} else {
    		diag.getTreatment().setRepose(false);
    	}
    	if(type.equals("true")){
    		diag.getTreatment().setType("Total");
    	} else {
    		diag.getTreatment().setType("Parcial");
    	}
    	
    	
    	diag.getTreatment().setTime(time);
    	diag.getTreatment().setMedicalPractices(medicalPracticesAsList);
    	
        getDiagnosticService().update(diag);
        return Response.ok(diag).build();
    }
    
    @POST
    @Path("/medicine/create/{id}/{name}/{concentration}/{weeks}")
    @Produces("application/json")
    public Medicine createTreatment(@PathParam("id") Integer id, @PathParam("name") String name,
    		@PathParam("concentration") Integer concentration, @PathParam("weeks") Integer weeks)  {
    	
    	Treatment treatment = getTreatmentService().findById(id);
    	Medicine medicine = new Medicine(name, concentration, weeks);
    	treatment.getMedicines().add(medicine);
    	getTreatmentService().update(treatment);
        return medicine;
    }
    
	@PUT
	@Path("/update/{id}/{medicalPractice}")
	@Produces("application/json")
	public Response updateMedicalPractice(@PathParam("id") final
			Integer id,@PathParam("medicalPractice") final String medicalPractice) {
		
		Diagnostic diag = this.getDiagnosticService().findById(id);
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
}