import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NubefactService } from '../../services/nubefact.service';
import { ComprobantesService } from '../../services/comprobantes.service';

@Component({
  selector: 'app-dialog-generar-comprobante',
  templateUrl: './dialog-generar-comprobante.component.html',
  styleUrls: ['./dialog-generar-comprobante.component.css']
})
export class DialogGenerarComprobanteComponent {


  form: FormGroup

  constructor(
    private fb: FormBuilder,
  ) {
    this.form = fb.group({
      tipoComp: ['', [Validators.required]],
      tipoDoc: ['', [Validators.required]],
      doc: ['25666175', [Validators.required]],
      nombre: ['Juan Carlos', [Validators.required]],
      direc: ['Prol. Luis Massaro 118', [Validators.required]],
      correo: [''],
      total: ['600', [Validators.required]],
    })
  }

  comprobantesService = inject(ComprobantesService)
  nubefactService =  inject(NubefactService)

  generar() {
    if (this.form) {
      this.comprobantesService.generarComprobante(this.form.value)
      this.nubefactService.generarComprobante(this.form.value).subscribe((data: any) => {
        if (data.aceptada_por_sunat) {
          this.comprobantesService.saveComprobante(data)
        }
      })
    }
  }
}
