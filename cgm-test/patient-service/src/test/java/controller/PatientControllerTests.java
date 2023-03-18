package controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PatientControllerTests {

	@Test
	public void testFindById() {
		given().when().get("/patient/123456789").then().statusCode(200).body("ssn", equalTo("123456789"), "name",
				notNullValue(), "surname", notNullValue(), "dateOfBirth", notNullValue());
	}

	@Test
	public void testFindAll() {
		given().when().get("/patient").then().statusCode(200).body(notNullValue());
	}
}
