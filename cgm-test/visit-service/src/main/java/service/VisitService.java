package service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import javax.transaction.Transactional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import client.PatientClient;
import model.Patient;
import model.Visit;
import repository.VisitRepository;

import java.util.List;

@ApplicationScoped
public class VisitService {

	@Inject
	VisitRepository visitRepository;

	@Inject
	@RestClient
	PatientClient patientService;

	@Transactional
	public List<Visit> findByPatient(String patientSsn) {
		return visitRepository.findByPatient(patientSsn);
	}

	@Transactional
	public Visit create(Visit visit) throws NotFoundException, ConstraintViolationException {
		// Check if patient exists with given SSN
		Patient patient = patientService.getPatientBySSN(visit.getPatientSsn());
		if (patient == null) {
			throw new NotFoundException("Patient with SSN " + visit.getPatientSsn() + " not found");
		}
		visitRepository.persist(visit);
		return visit;
	}

	@Transactional
	public Visit update(Long id, Visit visitDetails) {
		Visit visit = visitRepository.findById(id);
		if (visit != null) {
			visit.setDate(visitDetails.getDate());
			visit.setTime(visitDetails.getTime());
			visit.setType(visitDetails.getType());
			visit.setReason(visitDetails.getReason());
			visit.setFamilyHistory(visitDetails.getFamilyHistory());
			visit.setPatientSsn(visitDetails.getPatientSsn());
			visitRepository.persist(visit);
		}
		return visit;
	}

}
