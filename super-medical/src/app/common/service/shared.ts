import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private selectedPatientSource = new BehaviorSubject(null);
  selectedPatient = this.selectedPatientSource.asObservable();

  private newVisitCreatedSource = new BehaviorSubject(null);
  newVisitCreated = this.newVisitCreatedSource.asObservable();

  private updatedVisitSource = new BehaviorSubject(null);
  updatedVisit = this.updatedVisitSource.asObservable();

  constructor() { }

  selectPatient(patient: any) {
    this.selectedPatientSource.next(patient);
  }

  createVisit(visit: any) {
    this.newVisitCreatedSource.next(visit);
  }

  updateVisit(visit: any) {
    this.updatedVisitSource.next(visit);
  }
}
