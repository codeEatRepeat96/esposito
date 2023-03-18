import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Patient } from '../../common/model/patient';
import { SharedService } from '../../common/service/shared';
import { PatientService } from '../service/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css'],
})
export class PatientListComponent {
  @Output() patientClick = new EventEmitter();

  patients: string[] = [];
  displayedColumns: string[] = [
    'ssn',
    'name',
    'surname',
    'dateOfBirth',
    'visits',
  ];

  constructor(
    private sharedService: SharedService,
    private patientService: PatientService
  ) {}

  ngOnInit(): void {
    this.patientService.getPatients().subscribe({
      next: (patients) => {
        this.patients = patients;
      },
      error: (e) => {
        console.log(e);
      },
    });
  }

  onPatientClick(patient: Patient) {
    this.sharedService.selectPatient(patient);
    this.patientClick.emit();
  }
}
