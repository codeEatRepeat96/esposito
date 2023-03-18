import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  @ViewChild('drawer') drawer!: MatDrawer;
  constructor() {}

  openVisitList() {
    this.drawer.open();
  }
  
}
