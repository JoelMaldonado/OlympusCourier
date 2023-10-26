import { Component, inject, } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import Swal from 'sweetalert2';

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

  router = inject(Router)

  documento = new FormControl('')
  data$: Cliente[] = [];
  listClientes: Cliente[] = [];

  constructor(
    private clienteService: ClienteService,
    public dialog: MatDialog
  ) {
    this.listarClientes()
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
      width: "770px"
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
      width: "770px"
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
    Swal.fire({
      title: 'Eliminar Item',
      text: '¿Estas seguro?',
      icon: 'warning',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Eliminar',
      showCancelButton: true,
      confirmButtonColor: '#047CC4'
    }).then((res)=>{
      if(res.isConfirmed){
        const index = this.listItemRepartos.indexOf(item);
        if (index !== -1) {
          this.listItemRepartos.splice(index, 1);
        }
      }
    })
  }

  cliente: Cliente | undefined = undefined;

  openDialogCliente() {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      width: "950px",
    })

    dialogRef.afterClosed().subscribe((data: Cliente) => {
      if (data) {
        this.cliente = data
      }
    })
  }

  /** Insertar Reparto **/
  submitForm() {

  }
 
  isLoading = false;

  async search() {
    if (!this.documento.value) {
      return;
    }
    this.isLoading = true;
    const results = await this.clienteService.searchCliente(this.documento.value);
    this.data$ = results;
    this.isLoading = false;
  }
}
