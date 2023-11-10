import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-comprobante',
  templateUrl: './comprobante.component.html',
  styleUrls: ['./comprobante.component.css']
})
export class ComprobanteComponent {

  listComprobantes = new MatTableDataSource<any>();

  columnas: string[] = [
    'num',
    'tipo',
    'cliente',
    'total',
    'fecha',
    'estado',
    'act',
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.listComprobantes.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'items por página';
  }

  constructor(
  ) {
    this.listarComprobantes()
  }

  async listarComprobantes() {

  }

  timestampAfechaActualFormateada(timestamp: any) {
    const milisegundos = timestamp * 1000;
    const fecha = new Date(milisegundos);
    const dia = String(fecha.getDate()).padStart(2, '0');
    const mes = String(fecha.getMonth() + 1).padStart(2, '0');
    const año = fecha.getFullYear();

    return `${dia}/${mes}/${año}`;
  }

  verPdf(id: any, tipo: string, fileName: string) {
    
  }

}
