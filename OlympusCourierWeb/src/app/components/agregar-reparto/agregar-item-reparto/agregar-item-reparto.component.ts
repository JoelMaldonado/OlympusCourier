import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemReparto } from './item-reparto';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-agregar-item-reparto',
  templateUrl: './agregar-item-reparto.component.html',
  styleUrls: ['./agregar-item-reparto.component.css']
})
export class AgregarItemRepartoComponent {
  
  formulario: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AgregarItemRepartoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ItemReparto,
    private fb: FormBuilder
  ) {

    this.formulario = this.fb.group({
      numGuia: [''],
      tipo: [''],
      descrip: [''],
      cant: [''],
      precio: ['']
    })
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }
}
