import { Component, inject, } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subject, debounceTime, distinctUntilChanged } from 'rxjs';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';

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

  constructor(
    private clienteService: ClienteService,
    public dialog: MatDialog
  ) {
    this.listarClientes()

    this.searchTerms
    .pipe(
      debounceTime(1000),
      distinctUntilChanged()
    )
    .subscribe(async (searchTerm: string) => {
      const results = await this.clienteService.searchCliente(searchTerm);
      this.data$ = results;
      console.log(results);
    });

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
      width: "950px",
    })

    dialogRef.afterClosed().subscribe((data: Cliente) => {
      if (data) {
        this.cliente = data
      }
    })
  }

  back(){
    this.router.navigate(['../'])
  }

  /** Insertar Reparto **/
  submitForm() {

  }

  documento = new FormControl('')
  data$: Cliente[] = [];
  listClientes: Cliente[] = [];

  private searchTerms = new Subject<string>();
  
  async search() {
    if (!this.documento.value) {
      return;
    }
    this.searchTerms.next(this.documento.value); // Emitir el valor de búsqueda al observable
  }
}
