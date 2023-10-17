import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemReparto } from './item-reparto';

@Component({
  selector: 'app-agregar-item-reparto',
  templateUrl: './agregar-item-reparto.component.html',
  styleUrls: ['./agregar-item-reparto.component.css']
})
export class AgregarItemRepartoComponent {
  constructor(
    public dialogRef: MatDialogRef<AgregarItemRepartoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ItemReparto,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
