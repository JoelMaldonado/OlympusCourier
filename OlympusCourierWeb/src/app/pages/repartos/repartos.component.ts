import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { Reparto } from '../../models/reparto';
import { Router, RouterOutlet } from '@angular/router';
import { RepartoService } from '../../services/reparto.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DialogDetalleRepartoComponent } from '../../components/dialog-detalle-reparto/dialog-detalle-reparto.component';
import { DialogGenerarComprobanteComponent } from '../../components/dialog-generar-comprobante/dialog-generar-comprobante.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-repartos',
  standalone: true,
  imports: [
    MatButtonModule, CommonModule,
    MatIconModule, MatDatepickerModule,
    MatNativeDateModule, MatDialogModule,
    MatPaginatorModule, MatMenuModule,
    MatTableModule, RouterOutlet],
  templateUrl: './repartos.component.html',
  styleUrl: './repartos.component.css'
})
export class RepartosComponent {


  dialog = inject(MatDialog)

  listRepartos: Reparto[] = [];
  loaderRepartos = false;
  columnas: string[] = [
    'id',
    'cliente',
    'fecha',
    'flete',
    'estado',
    'act',
  ];

  router = inject(Router);
  repartoService = inject(RepartoService);


  bool: boolean = true;

  constructor(
  ) {
    this.listarRepartos()
    setTimeout(() => {
      this.bool = false;
    }, 3000);
  }

  getColor(estado: string): string {
    switch (estado) {
      case 'P':
        return 'gray';
      case 'E':
        return 'green';
      case 'A':
        return 'red';
      default:
        return 'black';
    }
  }

  getEstado(estado: string): string {
    switch (estado) {
      case 'P':
        return 'Pendiente';
      case 'E':
        return 'Entregado';
      case 'A':
        return 'Anulado';
      default:
        return 'Sin Valor';
    }
  }

  async listarRepartos() {
    this.loaderRepartos = true
    this.repartoService.listarRepartos().subscribe({
      next: data => {
        this.listRepartos = data
      },
      error: error => console.log(error)
    });
    this.loaderRepartos = false;
  }

  agregar() {
    this.router.navigateByUrl('/menu/agregar-reparto')
  }

  formatoId(id: number): string {
    const idStr = id.toString().slice(0, 6).padStart(6, '0');
    return `#${idStr}`;
  }


  getTotal(rep: Reparto): number {
    if (rep.items != undefined) {
      return rep.items?.reduce((acumulador, objeto) => acumulador + (objeto.cant * objeto.precio), 0);
    } else {
      return 0
    }
  }

  openDetalle() {

    const dialogRef = this.dialog.open(DialogDetalleRepartoComponent, {
      width: "950px"
    })

    dialogRef.afterClosed().subscribe(data => {
    })
  }

  formatFecha(fecha: string | undefined, pattern: string): string {
    if (fecha === undefined) {
      return "Sin fecha";
    } else {
      const date = new Date(fecha);

      if (isNaN(date.getTime())) {
        return "Fecha no válida";
      }

      if (pattern === "dd/MM/yyyy") {
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}-${month}-${year}`;
      } else if (pattern === "HH:mm") {
        const hours = date.getHours();
        const minutes = date.getMinutes().toString().padStart(2, '0');
        const amOrPm = hours >= 12 ? "PM" : "AM";
        const formattedHours = (hours % 12 || 12).toString().padStart(2, '0');
        return `${formattedHours}:${minutes} ${amOrPm}`;
      } else {
        return "Formato no admitido";
      }
    }
  }

  generarComprobante() {
    this.dialog.open(DialogGenerarComprobanteComponent, {
      width: "950px"
    })
  }
}
