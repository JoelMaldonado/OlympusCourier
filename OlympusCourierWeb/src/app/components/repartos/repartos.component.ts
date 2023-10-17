import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-repartos',
  templateUrl: './repartos.component.html',
  styleUrls: ['./repartos.component.css']
})
export class RepartosComponent {

  constructor(
    private router: Router
  ) {

  }

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;

  agregar() {
    this.router.navigateByUrl('/menu/agregar-reparto')
  }
}

const ELEMENT_DATA: any[] = [
  { nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, estado: 'Pendiente' },
  { nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, estado: 'Pendiente' },
  { nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, estado: 'Pendiente' },
  { nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, estado: 'Pendiente' },
  { nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, estado: 'Pendiente' },
];