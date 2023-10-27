import { Component, inject, } from '@angular/core';
import { Timestamp } from '@angular/fire/firestore';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { Reparto } from 'src/app/models/reparto';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { RepartoService } from 'src/app/shared/services/reparto.service';
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


  constructor(
    public dialog: MatDialog
  ) {
    this.listarClientes()
  }

  clienteService = inject(ClienteService)
  repartoService = inject(RepartoService)

  clave = new FormControl('', Validators.maxLength(4))

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

  showSugerencias = false

  selectCliente(item: Cliente) {
    this.cliente = item;
    this.documento.setValue(item.nombres ? item.nombres : this.documento.value)
    this.showSugerencias = false
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

  cancel() {
    Swal.fire({
      title: '¡Alerta de Seguridad!',
      text: '¿Estás seguro de que deseas regresar a la pantalla anterior? Todos los datos ingresados se perderán.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#047CC4',
      cancelButtonColor: '#CF475B',
      confirmButtonText: 'Sí, regresar',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigate(['../'])
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
    }).then((res) => {
      if (res.isConfirmed) {
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
      data: this.cliente,
    })

    dialogRef.afterClosed().subscribe((data: Cliente) => {
      if (data) {
        this.cliente = data
      }
    })
  }

  editCliente() {

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
    this.showSugerencias = true;
    const results = await this.clienteService.searchCliente(this.documento.value);
    this.data$ = results;
    this.isLoading = false;
  }


  getTotal() {
    const sumaDeCostos = this.listItemRepartos.reduce((acumulador, objeto) => acumulador + (objeto.cant * objeto.precio), 0);
    return sumaDeCostos
  }

  async guardarReparto() {
    const toast = Swal.mixin({
      toast: true,
      position: 'bottom',
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })
    if (this.cliente?.id != undefined) {
      if(this.listItemRepartos.length >0 ){

        const reparto: Reparto = {
          anotacion: '',
          clave: this.clave.value ? this.clave.value : '',
          estado: 'Pendiente',
          fecha: Timestamp.now(),
          idCliente: this.cliente.id,
          items: this.listItemRepartos,
        }
        const res = await this.repartoService.insert(reparto)
        if(res){
          this.router.navigate(['../'])
        }
      }else{
        toast.fire({
          icon: 'question',
          title: 'Ingrese un item'
        })
      }
    } else {

      toast.fire({
        icon: 'question',
        title: 'Ingrese un cliente'
      })
    }
  }
}
