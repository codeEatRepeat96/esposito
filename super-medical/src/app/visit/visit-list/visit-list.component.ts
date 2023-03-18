import { Component, OnInit } from '@angular/core';
import { Visit } from '../../common/model/visit';
import { SharedService } from '../../common/service/shared';
import { VisitService } from '../service/visit.service';

@Component({
  selector: 'app-visit-list',
  templateUrl: './visit-list.component.html',
  styleUrls: ['./visit-list.component.css'],
})
export class VisitListComponent implements OnInit {
  selectedPatient?: any;
  patientVisits: Visit[] = [];
  showFormToCreateVisit: boolean = false;

  visitToUpdate?: Visit;

  constructor(
    private sharedService: SharedService,
    private visitService: VisitService
  ) {}

  ngOnInit() {
    this.subscribeToSharedService();
  }

  subscribeToSharedService() {
    this.sharedService.selectedPatient.subscribe((patient) => {
      if (patient) {
        this.selectedPatient = patient;

        this.getPatientVisits(this.selectedPatient.ssn);
      }
    });

    this.sharedService.newVisitCreated.subscribe((visit) => {
      if (visit) {
        this.getPatientVisits(this.selectedPatient.ssn);
      }
    });

    this.sharedService.updatedVisit.subscribe((visit) => {
      if (visit) {
        this.getPatientVisits(this.selectedPatient.ssn);
      }
    });
  }

  createNewVisit() {
    this.showFormToCreateVisit = true;
    this.visitToUpdate = undefined;
  }

  updateVisit(visit: Visit) {
    this.showFormToCreateVisit = true;
    this.visitToUpdate = visit;
  }

  getPatientVisits(patientSsn: string) {
    this.patientVisits = [];
    this.visitService.getPatientVisits(patientSsn).subscribe({
      next: (visits) => {
        this.patientVisits = visits;
      },
      error: (e) => {
        console.log(e);
      },
    });
  }
}
