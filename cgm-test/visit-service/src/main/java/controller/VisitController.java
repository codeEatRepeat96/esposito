package controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Visit;
import service.VisitService;

import java.util.List;

@Path("/visit")
@Produces(MediaType.APPLICATION_JSON)
public class VisitController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VisitController.class);

	@Inject
	VisitService visitService;

	@GET
	@Path("/patient/{patientSsn}")
	public List<Visit> findByPatient(@PathParam("patientSsn") String patientSsn) {
		LOGGER.info("Patient visit find: patientSsn={}", patientSsn);
		return visitService.findByPatient(patientSsn);
	}

	@POST
	public Response createVisit(Visit visit) {
		LOGGER.info("Create new Visit: visit={}", visit);
		if (visit == null) {
			LOGGER.warn("Visit object is null");
			return Response.status(Status.BAD_REQUEST).entity("Visit object cannot be null").build();
		}
		try {
			Visit newVisit = visitService.create(visit);
			return Response.ok(newVisit).build();
		} catch (NotFoundException notFoundException) {
			LOGGER.warn("Patient does not exist");
			return Response.status(Status.NOT_FOUND).entity("Patient does not exist").build();
		}
	}

	@Path("/{id}")
	@PUT
	public Response updateVisit(@PathParam("id") Long id, Visit visit) {
		LOGGER.info("Update Visit: id={}", id);
		if (visit == null) {
			LOGGER.warn("Visit object is null");
			return Response.status(Status.BAD_REQUEST).entity("Visit object cannot be null").build();
		}
		Visit updatedVisit = visitService.update(id, visit);
		if (updatedVisit == null) {
			LOGGER.warn("Visit with id={} not found", id);
			return Response.status(Status.NOT_FOUND).entity("Visit with id " + id + " not found").build();
		}
		return Response.ok(updatedVisit).build();
	}

}
