import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ItemReparto } from './item-reparto';

@Component({
  selector: 'app-agregar-item-reparto',
  templateUrl: './agregar-item-reparto.component.html',
  styleUrls: ['./agregar-item-reparto.component.css']
})
export class AgregarItemRepartoComponent {

  
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
