import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      tipo: [data?.tipo_doc ? data.tipo_doc : 'Dni', [Validators.required]],
      doc: [data?.documento, [Validators.required, Validators.minLength(8)]],
      nombres: [data?.nombres, [Validators.required, Validators.maxLength(100)]],
      cel: [data?.telefono, [Validators.required, Validators.minLength(9)]],
      genero: [data?.genero ? data.genero : 'Sin especificar'],
      correo: [data?.correo, Validators.email],
      distrito: [data?.distrito_id, [Validators.required]],
      direc: [data?.direc, [Validators.required, Validators.maxLength(100)]],
      ref: [data?.referencia],
      maps: [data?.maps],
    })
  }

  listarDestinos() {
    this.destinoservice.listarDestinos().subscribe({
      next: data => this.listDistritos = data,
      error: error => {
        console.log(error)
        Swal.fire({
          icon: 'error',
          title: 'Error al obtener',
          text: 'Hubo un problema al obtener los destinos. Por favor, inténtelo de nuevo más tarde.',
        })
      }
    });
  }

  async guardar() {
    if (this.form.valid) {
      if (this.data == undefined) {

        const clienteData: Cliente = {
          tipo_doc: this.form.get('tipo')?.value,
          documento: this.form.get('doc')?.value,
          nombres: this.form.get('nombres')?.value,
          telefono: this.form.get('cel')?.value,
          genero: this.form.get('genero')?.value,
          correo: this.form.get('correo')?.value,
          distrito_id: this.form.get('distrito')?.value,
          direc: this.form.get('direc')?.value,
          referencia: this.form.get('ref')?.value,
        };
        const res = await this.clienteService.addCliente(clienteData)
        if (res !== false) {
          //clienteData.id = res as string;
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
