import { Component, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ItemReparto } from 'src/app/components/agregar-reparto/agregar-reparto.component';

@Component({
  selector: 'app-dialog-add-item-reparto',
  templateUrl: './dialog-add-item-reparto.component.html',
  styleUrls: ['./dialog-add-item-reparto.component.css']
})
export class DialogAddItemRepartoComponent {


  constructor(
    public dialogRef: MatDialogRef<DialogAddItemRepartoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ItemReparto | undefined,
  ) {

    this.nGuia.valueChanges.subscribe(() => this.updateFormValidity());
    this.cat.valueChanges.subscribe(() => this.updateFormValidity());
    this.descrip.valueChanges.subscribe(() => this.updateFormValidity());
    this.cant.valueChanges.subscribe(() => this.updateFormValidity());
    this.precio.valueChanges.subscribe(() => this.updateFormValidity());
  }

  updateFormValidity() {
    this.formValid = this.nGuia.valid && this.cat.valid && this.descrip.valid && this.cant.valid && this.precio.valid;
  }

  closeDialog() {
    this.dialogRef.close();
  }

  formValid = false;

  nGuia = new FormControl(this.data?.nGuia);
  cat = new FormControl(this.data?.cat, Validators.required);
  descrip = new FormControl(this.data?.descrip);
  cant = new FormControl(this.data?.cant, [Validators.required, Validators.min(1)]);
  precio = new FormControl(this.data?.precio, [Validators.required, Validators.min(1)]);

  onAceptar() {
    const itemReparto: ItemReparto = {
      nGuia: String(this.nGuia.value),
      cat: String(this.cat.value),
      descrip: String(this.descrip.value),
      precio: Number(this.precio.value),
      cant: Number(this.cant.value),
    }
    this.dialogRef.close(itemReparto)
  }
}
