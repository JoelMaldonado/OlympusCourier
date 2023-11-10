import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Cliente } from 'src/app/models/cliente';
import Swal from 'sweetalert2';
import { DestinosService } from '../../services/destinos.service';
import { Destino } from 'src/app/models/destino';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-dialog-add-cliente',
  templateUrl: './dialog-add-cliente.component.html',
  styleUrls: ['./dialog-add-cliente.component.css']
})
export class DialogAddClienteComponent {

  form: FormGroup
  clienteService = inject(ClienteService);

  listDistritos: Destino[] = [];
  constructor(
    public dialogRef: MatDialogRef<DialogAddClienteComponent>,
    private destinoservice: DestinosService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: Cliente | undefined,
  ) {

    this.listarDestinos()
    this.form = this.fb.group({
      tipo: [data?.tipo ? data.tipo : 'Dni', [Validators.required]],
      doc: [data?.doc, [Validators.required, Validators.minLength(8)]],
      nombres: [data?.nombres, [Validators.required, Validators.maxLength(100)]],
      cel: [data?.celular, [Validators.required, Validators.minLength(9)]],
      genero: [data?.genero ? data.genero : 'Sin especificar'],
      correo: [data?.correo, Validators.email],
      distrito: [data?.distrito, [Validators.required]],
      direc: [data?.direc, [Validators.required, Validators.maxLength(100)]],
      ref: [data?.ref],
      maps: [data?.maps],
    })
  }

  async listarDestinos() {
    this.listDistritos = await this.destinoservice.listarDestinos()
  }

  async guardar() {
    if (this.form.valid) {
      if (this.data == undefined) {

        const clienteData: Cliente = {
          tipo: this.form.get('tipo')?.value,
          doc: this.form.get('doc')?.value,
          nombres: this.form.get('nombres')?.value,
          celular: this.form.get('cel')?.value,
          genero: this.form.get('genero')?.value,
          correo: this.form.get('correo')?.value,
          distrito: this.form.get('distrito')?.value,
          direc: this.form.get('direc')?.value,
          ref: this.form.get('ref')?.value,
        };
        const res = await this.clienteService.addCliente(clienteData)
        if (res !== false) {
          clienteData.id = res as string;
          this.dialogRef.close(clienteData);
        }
      } else {
        this.dialogRef.close();
      }
    } else {
      Swal.fire({
        title: 'Error de Validación',
        text: 'Por favor, complete todos los campos requeridos',
        icon: 'question',
        customClass: {
          confirmButton: 'btn-alert',
        },
        confirmButtonText: 'Confirmar',
        confirmButtonColor: '#05ACD7',
        confirmButtonAriaLabel: '#FFF'
      })
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
