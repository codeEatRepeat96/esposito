import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PATIENT_BASE_URL } from 'src/app/common/constant/network.constants';

@Injectable({
  providedIn: 'root',
})
export class PatientService {
  private baseUrl = PATIENT_BASE_URL;

  constructor(private http: HttpClient) {}

  getPatients(): Observable<any> {
    return this.http.get(this.baseUrl + '/patient');
  }
}
