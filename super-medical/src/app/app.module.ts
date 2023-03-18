import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { MaterialModule } from './material/material.module';


import { VisitCreateComponent } from './visit/visit-create/visit-create.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoaderComponent } from './common/component/loader/loader.component';
import { PatientListComponent } from './patient/patient-list/patient-list.component';
import { HttpClientModule } from '@angular/common/http';
import { VisitListComponent } from './visit/visit-list/visit-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PatientListComponent,
    VisitListComponent,
    VisitCreateComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
