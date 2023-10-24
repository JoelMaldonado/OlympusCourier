import { Component } from '@angular/core';
import { Timestamp } from '@angular/fire/firestore';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
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
    if (fecha === undefined) {
      return "Sin fecha";
    } else {
      const formattedDate = fecha.toDate().toLocaleDateString('es-ES', {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric'
      });
  
      return formattedDate.split('/').join('-'); // Reemplaza barras diagonales por guiones
    }
  }
  

  
}
