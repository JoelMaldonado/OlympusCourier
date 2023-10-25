import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { Cliente } from 'src/app/models/cliente';

@Component({
  selector: 'app-dialog-add-cliente',
  templateUrl: './dialog-add-cliente.component.html',
  styleUrls: ['./dialog-add-cliente.component.css']
})
export class DialogAddClienteComponent {

  tipo = new FormControl('', Validators.required);
  doc = new FormControl('', Validators.required);
  nombres = new FormControl('', Validators.required);
  cel = new FormControl('', Validators.required);
  distrito = new FormControl('', Validators.required);
  direc = new FormControl('', Validators.required);
  ref = new FormControl('');

  constructor(
    public dialogRef: MatDialogRef<DialogAddClienteComponent>
  ) {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  guardar() {
    const clienteData: Cliente = {
      tipo: String(this.tipo.value),
      doc: String(this.doc.value),
      nombres: String(this.nombres.value),
      celular: String(this.cel.value),
      distrito: String(this.distrito.value),
      direc: String(this.direc.value),
      ref: String(this.ref.value),
    };
    this.dialogRef.close(clienteData);
  }
}
