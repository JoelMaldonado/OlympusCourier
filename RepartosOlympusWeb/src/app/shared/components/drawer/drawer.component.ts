import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent {
  constructor() {
  }

  router = inject(Router)

  @Input() conected = false;
  @Output() onToggleSideNav: EventEmitter<boolean> = new EventEmitter();
  collapsed = true;
  screenWidth = 0;

  toggleNavigation() {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit(!this.collapsed);
  }

  close() {
    this.collapsed = false;
    this.onToggleSideNav.emit(true);
  }

  logout() {
    this.router.navigate(['/login']);
    localStorage.removeItem('token');
  }

  selected = 'repartos'

  navegar(url: string) {
    this.router.navigate(['/menu', url]);
    this.selected = url;
  }
}
