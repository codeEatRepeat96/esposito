import {
  Component,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Visit } from '../../common/model/visit';
import { SharedService } from '../../common/service/shared';
import { VisitService } from '../service/visit.service';

@Component({
  selector: 'app-visit-create',
  templateUrl: './visit-create.component.html',
  styleUrls: ['./visit-create.component.css'],
})
export class VisitCreateComponent implements OnChanges {
  @Input() selectedPatientSsn: string = '';
  @Input() visitToUpdate?: Visit;

  @Output() cancelClick = new EventEmitter();

  isLoading: boolean = false;

  buttonText: string = '';

  visitForm: FormGroup;
  visitTypes = ['HOME', 'DOCTOR_OFFICE'];
  visitReasons = ['FIRST_VISIT', 'RECURRING_VISIT', 'URGENT'];

  constructor(
    private fb: FormBuilder,
    private sharedService: SharedService,
    private visitService: VisitService
  ) {
    this.visitForm = this.fb.group({
      date: ['', Validators.required],
      time: ['', Validators.required],
      type: ['', Validators.required],
      reason: ['', Validators.required],
      familyHistory: [''],
    });
    this.buttonText = this.visitToUpdate ? 'Update' : 'Create';
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['visitToUpdate']) {
      this.buttonText = this.visitToUpdate ? 'Update' : 'Create';
      this.visitForm = this.fb.group({
        date: [
          this.visitToUpdate ? this.visitToUpdate.date : '',
          Validators.required,
        ],
        time: [
          this.visitToUpdate ? this.visitToUpdate.time : '',
          Validators.required,
        ],
        type: [
          this.visitToUpdate ? this.visitToUpdate.type : '',
          Validators.required,
        ],
        reason: [
          this.visitToUpdate ? this.visitToUpdate.reason : '',
          Validators.required,
        ],
        familyHistory: [
          this.visitToUpdate && this.visitToUpdate.familyHistory
            ? this.visitToUpdate.familyHistory
            : '',
        ],
      });
    }
  }

  onCreateClick() {
    this.isLoading = true;
    if (this.visitForm.valid) {
      const visit: Visit = {
        date: this.visitForm.value.date,
        time: this.visitForm.value.time,
        type: this.visitForm.value.type,
        reason: this.visitForm.value.reason,
        familyHistory: this.visitForm.value.familyHistory,
        patientSsn: this.selectedPatientSsn,
      };

      if (this.visitToUpdate) {
        this.visitService.updateVisit(this.visitToUpdate.id!, visit).subscribe({
          next: (visit) => {
            this.sharedService.updateVisit(visit);
          },
          error: (e) => {
            console.log(e);
          },
          complete: () => {
            this.isLoading = false;
          },
        });
      } else {
        this.visitService.createVisit(visit).subscribe({
          next: (visit) => {
            this.sharedService.createVisit(visit);
          },
          error: (e) => {
            console.log(e);
          },
          complete: () => {
            this.isLoading = false;
          },
        });
      }
    }
  }

  onCancelClick() {
    this.cancelClick.emit();
  }
}
