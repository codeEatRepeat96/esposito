package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Patient;
import service.PatientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/patient")
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

	@Inject
	PatientService patientService;

	@Path("/{ssn}")
	@GET
	public Patient findById(@PathParam("ssn") String ssn) {
		LOGGER.info("Patient find: ssn={}", ssn);
		return patientService.findBySsn(ssn);
	}

	@GET
	public List<Patient> findAll() {
		LOGGER.info("Patients find");
		return patientService.findAll();
	}

}