import { Component, inject } from '@angular/core';
import { Timestamp } from '@angular/fire/firestore';
import { Router } from '@angular/router';
import { Reparto } from 'src/app/models/reparto';
import { RepartoService } from 'src/app/shared/services/reparto.service';
import { ItemReparto } from '../agregar-reparto/agregar-reparto.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogDetalleRepartoComponent } from 'src/app/shared/components/dialog-detalle-reparto/dialog-detalle-reparto.component';
import { DialogGenerarComprobanteComponent } from 'src/app/shared/components/dialog-generar-comprobante/dialog-generar-comprobante.component';

@Component({
  selector: 'app-repartos',
  templateUrl: './repartos.component.html',
  styleUrls: ['./repartos.component.css']
})
export class RepartosComponent {

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
  constructor(
    private router: Router,
    private repartoService: RepartoService
  ) {
    this.listarRepartos()
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

  getEstado(estado:string): string{
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

  dialog = inject(MatDialog)

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
