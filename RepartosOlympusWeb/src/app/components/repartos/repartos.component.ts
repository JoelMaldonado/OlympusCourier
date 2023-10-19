import { Component } from '@angular/core';
import { Timestamp } from '@angular/fire/firestore';
import { Router } from '@angular/router';
import { Reparto } from 'src/app/models/reparto';
import { RepartoService } from 'src/app/shared/services/reparto.service';

@Component({
  selector: 'app-repartos',
  templateUrl: './repartos.component.html',
  styleUrls: ['./repartos.component.css']
})
export class RepartosComponent {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource: Reparto[] = [];

  constructor(
    private router: Router,
    private repartoService: RepartoService
  ) {
    this.listarRepartos()
  }

  async listarRepartos() {
    this.dataSource = await this.repartoService.listarRepartos();
  }

  agregar() {
    this.router.navigateByUrl('/menu/agregar-reparto')
  }

  formatFecha(fecha: Timestamp | undefined) {
    if (fecha === undefined){
      return "Sin fecha"
    }else{
      return fecha.toDate().toLocaleDateString('es-ES', { year: 'numeric', month: 'short', day: '2-digit' });
    }
  }
}
