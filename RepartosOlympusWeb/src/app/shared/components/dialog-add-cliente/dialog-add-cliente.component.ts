import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DialogData } from './dialog-data';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dialog-add-cliente',
  templateUrl: './dialog-add-cliente.component.html',
  styleUrls: ['./dialog-add-cliente.component.css']
})
export class DialogAddClienteComponent {
  
  formulario: FormGroup;
  
  constructor(
    public dialogRef: MatDialogRef<DialogAddClienteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private fb: FormBuilder,
  ) {
    this.formulario = fb.group({
      documento: ['', Validators.required],
      nombreLegal: ['', Validators.required],
      celular: ['', Validators.maxLength(9)],
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  submitForm() {
    if (this.formulario.valid) {
      this.formulario.reset()
    }
  }
}
