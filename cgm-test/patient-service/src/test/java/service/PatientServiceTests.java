package service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import model.Patient;
import repository.PatientRepository;

@QuarkusTest
public class PatientServiceTests {
	
	private PatientRepository patientRepository;
	private PatientService patientService;

	@BeforeEach
	public void init() {
		patientRepository = mock(PatientRepository.class);
		patientService = new PatientService();
		patientService.patientRepository = patientRepository;
	}

	@Test
	@Transactional
	public void testFindBySsn() {
		String ssn = "123456789";
		Patient patient = new Patient();
		patient.setSsn(ssn);
		patient.setName("John");
		patient.setSurname("Doe");
		patient.setDateOfBirth(LocalDate.of(1996, 5, 5));
		
		when(patientRepository.findById(ssn)).thenReturn(patient);
		Patient result = patientService.findBySsn(ssn);
		Assertions.assertEquals(patient.getSsn(), result.getSsn());
	}

	@Test
	@Transactional
	public void testFindAll() {
		Patient patient1 = new Patient();
		patient1.setSsn("123456789");
		patient1.setName("John");
		patient1.setDateOfBirth(LocalDate.of(1990, 1, 1));
		Patient patient2 = new Patient();
		patient2.setSsn("987654321");
		patient2.setName("Jane");
		patient2.setDateOfBirth(LocalDate.of(1995, 10, 10));
		List<Patient> patients = new ArrayList<>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientRepository.listAll()).thenReturn(Arrays.asList(patient1, patient2));

		List<Patient> result = patientService.findAll();
		Assertions.assertIterableEquals(patients, result);
	}

}
