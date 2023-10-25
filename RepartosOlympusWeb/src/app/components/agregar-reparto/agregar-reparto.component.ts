import { Component, } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Observable, finalize, map, startWith } from 'rxjs';
import { Cliente } from 'src/app/models/cliente';
import { Destino } from 'src/app/models/destino';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { DestinosService } from 'src/app/shared/services/destinos.service';

export interface ItemReparto {
  nGuia: string
  cat: string
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

  displayFn(option: any): string {
    return option && option.doc ? option.doc : '';
  }

  listItemRepartos: ItemReparto[] = [
    {
      nGuia: '001-000001',
      cat: 'Caja',
      descrip: 'Marron',
      precio: 20,
      cant: 2,
    }
  ];
  listDistritos: Destino[] = [];

  constructor(
    private destinoservice: DestinosService,
    private clienteService: ClienteService,
    public dialog: MatDialog
  ) {
    this.listarDestinos()
    this.listarClientes()
  }

  async listarDestinos() {
    this.listDistritos = await this.destinoservice.listarDestinos()
  }

  async listarClientes() {
    this.listClientes = await this.clienteService.listarClientes()
  }

  remove(itemEliminar: any) {
    this.listItemRepartos = this.listItemRepartos.filter(item => item !== itemEliminar)
  }

  /** Añadir Item Reparto*/
  openDialogAddItemReparto() {
    const dialogRef = this.dialog.open(DialogAddItemRepartoComponent, {
      width: "650px"
    });

    dialogRef.afterClosed().subscribe((data: ItemReparto) => {
      if (data) {
        this.listItemRepartos.push(data)
      }
    });
  }

  editItemReparto(item: ItemReparto) {
    const dialogRef = this.dialog.open(DialogAddItemRepartoComponent, {
      data: item,
      width: "650px"
    })

    dialogRef.afterClosed().subscribe((data: ItemReparto) => {
      if (data) {
        const index = this.listItemRepartos.findIndex((element) => element === item);
        if (index !== -1) {
          this.listItemRepartos[index] = data;
        }
      }
    });
  }

  deleteItemReparto(item: ItemReparto) {
    const index = this.listItemRepartos.indexOf(item);
    if (index !== -1) {
      this.listItemRepartos.splice(index, 1);
    }
  }

  cliente: Cliente | undefined = undefined;

  openDialogCliente() {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      width: "650px"
    })

    dialogRef.afterClosed().subscribe((data: Cliente) => {
      if (data) {
        this.cliente = data
      }
    })
  }

  /**Buscar documento Reniec**/
  buscarDoc() {

  }

  /** Insertar Reparto **/
  submitForm() {

  }

  documento = new FormControl('')
  data$: Cliente[] = [];
  listClientes: Cliente[] = [];

  search(): void {
  }
}
