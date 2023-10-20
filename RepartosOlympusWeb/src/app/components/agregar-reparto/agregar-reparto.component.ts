import { Component, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Destino } from 'src/app/models/destino';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { DestinosService } from 'src/app/shared/services/destinos.service';
import { RepartoService } from 'src/app/shared/services/reparto.service';

export interface ItemReparto {
  numGuia: string
  tipo: string
  descrip: string
  precio: number
  cant: number
}

@Component({
  selector: 'app-agregar-reparto',
  templateUrl: './agregar-reparto.component.html',
  styleUrls: ['./agregar-reparto.component.css']
})
export class AgregarRepartoComponent {


  formulario: FormGroup;

  listItemRepartos: ItemReparto[] = [
    {
      numGuia: '001-000001',
      tipo: 'Caja',
      descrip: 'Marron',
      precio: 20,
      cant: 2,
    }
  ];
  listDistritos: Destino[] = [];

  constructor(
    private destinoservice: DestinosService,
    private repartoService: RepartoService,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {

    this.formulario = this.fb.group({
      documento: ['', Validators.required],
      nombres: [''],
      apellidos: [''],
      celular: ['', [Validators.maxLength(9)]],
      clave: ['', Validators.required],
      distrito: [''],
      direc: ['', Validators.required],
      referencia: [''],
      anotaciones: ['']
    })


    this.listarDestinos()
  }

  async listarDestinos() {
    this.listDistritos = await this.destinoservice.listarDestinos()
  }

  remove(itemEliminar: any) {
    this.listItemRepartos = this.listItemRepartos.filter(item => item !== itemEliminar)
  }

  /** Abrir Alerta*/
  openDialog() {
    const dialogRef = this.dialog.open(DialogAddItemRepartoComponent, {
      data: {},
      width: "650px"
    });

    dialogRef.afterClosed().subscribe(result => {
      
    });
  }

  /**Buscar documento Reniec**/
  buscarDoc() {
    const doc = this.formulario.get('documento')?.value as string
    if (doc.length === 8) {
      this.formulario.get('nombres')?.setValue('Joel Joas')
      this.formulario.get('apellidos')?.setValue('Maldonado Fernandez')
      this.formulario.get('celular')?.setValue('936416623')
      this.formulario.get('distrito')?.setValue('Chincha Alta')
      this.formulario.get('direc')?.setValue('Prol. Luis Massaro 118')
      this.formulario.get('referencia')?.setValue('Atrás de plaza vea')
    }
  }

  /** Insertar Reparto **/
  submitForm() {
    if (this.formulario.valid) {
      this.repartoService.insert(this.formulario.value)
      this.formulario.reset()
    }
  }

}
