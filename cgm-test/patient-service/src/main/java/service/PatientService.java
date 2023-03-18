package service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

import model.Patient;
import repository.PatientRepository;

@ApplicationScoped
public class PatientService {

	@Inject
	PatientRepository patientRepository;

	@Transactional
	public Patient findBySsn(String ssn) {
		return patientRepository.findById(ssn);
	}

	@Transactional
	public List<Patient> findAll() {
		return patientRepository.listAll();
	}
}
