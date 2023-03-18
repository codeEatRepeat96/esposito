package model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import common.Reason;
import common.Type;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "visit")
public class Visit extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    @NotNull(message = "date cannot be null")
    private LocalDate date;

    @Column(name = "time")
    @NotNull(message = "time cannot be null")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull(message = "Visit type cannot be null")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "reason")
    @NotNull(message = "Visit reason cannot be null")
    private Reason reason;

    @Column(name = "family_history")
    private String familyHistory;

    @Column(name = "patient_ssn", length = 11)
    @Size(min = 11, max = 11, message = "SSN must be exactly 11 characters")
    @NotNull(message = "SSN cannot be null")
    private String patientSsn;

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public Type getType() {
		return type;
	}

	public Reason getReason() {
		return reason;
	}

	public String getFamilyHistory() {
		return familyHistory;
	}

	public String getPatientSsn() {
		return patientSsn;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}

	public void setPatientSsn(String patientSsn) {
		this.patientSsn = patientSsn;
	}

}
