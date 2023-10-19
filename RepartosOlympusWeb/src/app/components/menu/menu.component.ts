import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {


  collapsed: boolean = false;

  constructor(
    private router: Router
  ) {

  }

  menu(url: string) {
    this.router.navigateByUrl('/menu/' + url)
  }

  onCollapsedChange(collapsed: boolean) {
    this.collapsed = collapsed;
  }

}
