package service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import client.PatientClient;
import common.Reason;
import common.Type;
import model.Patient;
import model.Visit;
import repository.VisitRepository;

public class VisitServiceTests {

	private VisitService visitService;
	private VisitRepository visitRepository;
	private PatientClient patientClient;

	@BeforeEach
	public void init() {
		visitRepository = mock(VisitRepository.class);
		patientClient = mock(PatientClient.class);
		visitService = new VisitService();
		visitService.visitRepository = visitRepository;
		visitService.patientService = patientClient;
	}

	@Test
	public void testFindByPatient() {
		String patientSsn = "123456789";
		List<Visit> expectedVisits = new ArrayList<>();
		expectedVisits.add(new Visit());
		when(visitRepository.findByPatient(patientSsn)).thenReturn(expectedVisits);

		List<Visit> visits = visitService.findByPatient(patientSsn);

		assertEquals(expectedVisits, visits);
	}

	@Test
	public void testCreate() throws NotFoundException, ConstraintViolationException {
		Visit visit = new Visit();
		visit.setPatientSsn("123456789");
		visit.setDate(LocalDate.now());
		visit.setTime(LocalTime.now());
		visit.setReason(Reason.URGENT);
		visit.setType(Type.HOME);
		visit.setFamilyHistory("create from test");
		when(patientClient.getPatientBySSN("123456789")).thenReturn(new Patient());

		Visit createdVisit = visitService.create(visit);

		assertNotNull(createdVisit);
		assertEquals(visit, createdVisit);
	}

	@Test
	public void testUpdate() {

		Long id = 1L;
		Visit visit = new Visit();
		visit.setPatientSsn("123456789");
		visit.setDate(LocalDate.now());
		visit.setTime(LocalTime.now());
		visit.setReason(Reason.URGENT);
		visit.setType(Type.HOME);
		visit.setFamilyHistory("before update");
		when(visitRepository.findById(id)).thenReturn(visit);

		Visit visitDetails = new Visit();
		visitDetails.setPatientSsn("123456789");
		visitDetails.setDate(LocalDate.now());
		visitDetails.setTime(LocalTime.now());
		visitDetails.setReason(Reason.URGENT);
		visitDetails.setType(Type.HOME);
		visitDetails.setFamilyHistory("after update");

		Visit updatedVisit = visitService.update(id, visitDetails);

		assertNotNull(updatedVisit);
		assertEquals(visitDetails.getDate(), updatedVisit.getDate());
		assertEquals(visitDetails.getTime(), updatedVisit.getTime());
		assertEquals(visitDetails.getType(), updatedVisit.getType());
		assertEquals(visitDetails.getReason(), updatedVisit.getReason());
		assertEquals(visitDetails.getFamilyHistory(), updatedVisit.getFamilyHistory());
		assertEquals(visitDetails.getPatientSsn(), updatedVisit.getPatientSsn());
	}

}
