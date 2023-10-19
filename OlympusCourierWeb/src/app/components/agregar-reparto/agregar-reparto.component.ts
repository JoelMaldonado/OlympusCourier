import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AgregarItemRepartoComponent } from './agregar-item-reparto/agregar-item-reparto.component';
import { ItemReparto } from './agregar-item-reparto/item-reparto';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { DestinoService } from 'src/app/shared/services/destino.service';
import { Destino } from 'src/app/models/destino';
import { map } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RepartoService } from 'src/app/shared/services/reparto.service';

@Component({
  selector: 'app-agregar-reparto',
  templateUrl: './agregar-reparto.component.html',
  styleUrls: ['./agregar-reparto.component.css']
})
export class AgregarRepartoComponent {

  formulario: FormGroup;

  document: string = '';
  clientName: string = '';
  clientCelular: string = '';
  district: string = '';
  address: string = '';
  reference: string = '';
  password: string = '';
  notes: string = '';
  displayedColumns: string[] = ['descrip', 'precio', 'cant', 'actions'];
  dataSource: ItemReparto[] = [];

  distritos: Destino[] = [];

  @ViewChild(MatTable) table!: MatTable<ItemReparto>;

  constructor(
    public dialog: MatDialog,
    private destinoservice: DestinoService,
    private repartoService:RepartoService,
    private fb: FormBuilder
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
    this.distritos = await this.destinoservice.listarUsuarios()
  }

  remove(itemEliminar: any) {
    this.dataSource = this.dataSource.filter(item => item !== itemEliminar)
  }

  /** Abrir Alerta*/
  openProductDialog() {
    const dialogRef = this.dialog.open(AgregarItemRepartoComponent, {
      width: '800px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.dataSource.push(result)
      this.table.renderRows();
    });
  }


  /** Insertar Reparto **/
  submitForm() {
    if(this.formulario.valid){
      this.repartoService.insert(this.formulario.value)
      this.formulario.reset()
    }
  }

}
