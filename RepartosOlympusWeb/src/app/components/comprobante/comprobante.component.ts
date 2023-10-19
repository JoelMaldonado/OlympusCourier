import { Component } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { SunatService } from 'src/app/shared/services/sunat.service';

@Component({
  selector: 'app-comprobante',
  templateUrl: './comprobante.component.html',
  styleUrls: ['./comprobante.component.css']
})
export class ComprobanteComponent {

  listComprobantes: any[] = [];

  constructor(
    private sunatService: SunatService
  ) {
    this.listarComprobantes()
  }

  listarComprobantes() {
    const call = this.sunatService.listarDocumentos();
    call.subscribe(data => {
      this.listComprobantes = data;
      console.log(data);
    })

  }

  timestampAfechaActualFormateada(timestamp: any) {
    const milisegundos = timestamp * 1000;
    const fecha = new Date(milisegundos);
    const dia = String(fecha.getDate()).padStart(2, '0'); // Agrega ceros iniciales si es necesario
    const mes = String(fecha.getMonth() + 1).padStart(2, '0'); // Suma 1 al mes ya que los meses comienzan desde 0
    const año = fecha.getFullYear();

    return `${dia}/${mes}/${año}`;
  }

  verPdf(id: any, tipo: string, fileName: string) {
    this.sunatService.getPdf(id, tipo, fileName)
  }

}
