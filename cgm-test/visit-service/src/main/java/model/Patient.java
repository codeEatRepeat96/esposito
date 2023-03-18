package model;

import java.time.LocalDate;

public class Patient {

	private String ssn;

	private String name;

	private String surname;

	private LocalDate dateOfBirth;

	public Patient() {

	}

	public Patient(String ssn, String name, String surname, LocalDate dateOfBirth) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	public String getSsn() {
		return ssn;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
