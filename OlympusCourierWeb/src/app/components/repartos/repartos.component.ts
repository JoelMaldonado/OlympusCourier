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
  { venta:10, nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, fecha:'15 dic. 2023', estado: 'Pendiente' },
  {venta:10, nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, fecha:'15 dic. 2023', estado: 'Pendiente' },
  { venta:10,nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, fecha:'15 dic. 2023',estado: 'Pendiente' },
  { venta:10,nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, fecha:'15 dic. 2023',estado: 'Pendiente' },
  { venta:10,nombre: 'Joel Maldonado', celular: '936 416 623', total: 15, fecha:'15 dic. 2023',estado: 'Pendiente' },
];