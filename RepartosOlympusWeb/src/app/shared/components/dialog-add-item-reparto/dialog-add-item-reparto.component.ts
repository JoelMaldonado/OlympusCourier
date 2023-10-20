import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dialog-add-item-reparto',
  templateUrl: './dialog-add-item-reparto.component.html',
  styleUrls: ['./dialog-add-item-reparto.component.css']
})
export class DialogAddItemRepartoComponent {

  formulario: FormGroup;

  constructor(
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
  }
}
