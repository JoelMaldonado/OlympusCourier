import { Component, EventEmitter, Input, Output } from '@angular/core';
import { navbarData } from './nav-data';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent {


  constructor(
  ) {
  }

  @Input() conected = false;
  @Output() onToggleSideNav: EventEmitter<boolean> = new EventEmitter();
  collapsed = true;
  screenWidth = 0;
  navData = navbarData;

  click() {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit(false)
  }

  close() {
    this.collapsed = false;
    this.onToggleSideNav.emit(true)
  }

}
