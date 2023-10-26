import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Cliente } from 'src/app/models/cliente';
import Swal from 'sweetalert2';
import { DestinosService } from '../../services/destinos.service';
import { Destino } from 'src/app/models/destino';

@Component({
  selector: 'app-dialog-add-cliente',
  templateUrl: './dialog-add-cliente.component.html',
  styleUrls: ['./dialog-add-cliente.component.css']
})
export class DialogAddClienteComponent {

  form: FormGroup

  listDistritos: Destino[] = [];
  constructor(
    public dialogRef: MatDialogRef<DialogAddClienteComponent>,
    private destinoservice: DestinosService,
    private fb: FormBuilder
  ) {

    this.listarDestinos()
    this.form = this.fb.group({
      tipo: ['', [Validators.required]],
      doc: ['', [Validators.required, Validators.minLength(8)]],
      nombres: ['', [Validators.required, Validators.maxLength(100)]],
      cel: ['', [Validators.required, Validators.minLength(9)]],
      distrito: ['', [Validators.required]],
      direc: ['', [Validators.required, Validators.maxLength(100)]],
      ref: [''],
    })
  }

  async listarDestinos() {
    this.listDistritos = await this.destinoservice.listarDestinos()
  }

  guardar() {
    if (this.form.valid) {
      const clienteData: Cliente = {
        tipo: this.form.get('tipo')?.value,
        doc: this.form.get('doc')?.value,
        nombres: this.form.get('nombres')?.value,
        celular: this.form.get('cel')?.value,
        distrito: this.form.get('distrito')?.value,
        direc: this.form.get('direc')?.value,
        ref: this.form.get('ref')?.value,
      };
      this.dialogRef.close(clienteData);
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
