import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VISIT_BASE_URL } from 'src/app/common/constant/network.constants';
import { Visit } from 'src/app/common/model/visit';

@Injectable({
  providedIn: 'root',
})
export class VisitService {
  private baseUrl = VISIT_BASE_URL;

  constructor(private http: HttpClient) {}

  getPatientVisits(patientSsn: string): Observable<any> {
    return this.http.get(this.baseUrl + '/visit/patient/' + patientSsn);
  }

  updateVisit(id: number, visit: Visit): Observable<any> {
    const url = this.baseUrl + '/visit/' + id;
    return this.http.put(url, visit);
  }

  createVisit(visit: Visit): Observable<any> {
    const url = this.baseUrl + '/visit';
    return this.http.post(url, visit);
  }
}
