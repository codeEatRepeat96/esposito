package client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import model.Patient;

@RegisterRestClient
@Path("/patient")
public interface PatientClient {

    @GET
    @Path("/{ssn}")
    @Produces(MediaType.APPLICATION_JSON)
    Patient getPatientBySSN(@PathParam("ssn") String ssn);
}
