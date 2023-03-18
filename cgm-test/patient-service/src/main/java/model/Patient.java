package model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "patient")
public class Patient extends PanacheEntityBase {

    @Id
    @Column(name = "ssn", length = 11)
    @Size(min = 11, max = 11, message = "SSN must be exactly 11 characters")
    @NotNull(message = "SSN cannot be null")
    private String ssn;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "surname")
    @NotNull(message = "Surname cannot be null")
    private String surname;

    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

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
