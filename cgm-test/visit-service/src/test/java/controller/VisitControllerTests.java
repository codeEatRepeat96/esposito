package controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import common.Reason;
import common.Type;
import model.Visit;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class VisitControllerTests {

	@Test
	public void testFindByPatient() {
		given().when().get("/visit/patient/123456789").then().statusCode(200).body(notNullValue());
	}

	@Test
	public void testCreateVisit() {
		Visit visit = new Visit();
		visit.setPatientSsn("123456789");
		visit.setDate(LocalDate.now());
		visit.setTime(LocalTime.now());
		visit.setReason(Reason.URGENT);
		visit.setType(Type.HOME);
		visit.setFamilyHistory("created from test");

		given().contentType(MediaType.APPLICATION_JSON).body(visit).when().post("/visit").then().statusCode(200)
				.body("patientSsn", equalTo("123456789"), "familyHistory", equalTo("created from test"));
	}

	@Test
	public void testUpdateVisit() {
		Visit visit = new Visit();
		visit.setPatientSsn("123456789");
		visit.setDate(LocalDate.now());
		visit.setTime(LocalTime.now());
		visit.setReason(Reason.URGENT);
		visit.setType(Type.HOME);
		visit.setFamilyHistory("before update");

		// Create a new visit to update
		Visit newVisit = given().contentType(MediaType.APPLICATION_JSON).body(visit).when().post("/visit").then()
				.statusCode(200).extract().as(Visit.class);

		// Update the visit's description
		newVisit.setFamilyHistory("after update");

		given().contentType(MediaType.APPLICATION_JSON).body(newVisit).when().put("/visit/" + newVisit.getId()).then()
				.statusCode(200).body("patientSsn", equalTo("123456789"), "familyHistory", equalTo("after update"));
	}

}
