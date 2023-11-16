import { Component, inject, } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { Reparto } from 'src/app/models/reparto';
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


  clave = new FormControl('', Validators.maxLength(4))
  cliente : Cliente | undefined;
  listItemRepartos: ItemReparto[] = []

  clienteService = inject(ClienteService);
  repartoService = inject(RepartoService);
  router = inject(Router);
  dialog = inject(MatDialog);

  clienteEmit(newCliente: Cliente | undefined) {
    this.cliente = newCliente;
  }

  displayFn(option: any): string {
    return option && option.doc ? option.doc : '';
  }

  listEmit(listItemRepartos: ItemReparto[]){
    this.listItemRepartos = listItemRepartos;
  }


  cancel() {
    if(this.cliente && this.listItemRepartos){
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
          this.router.navigate(['../']);
        }
      });
    }else{
      this.router.navigate(['../']);
    }
  }


  getTotal() {
    return this.listItemRepartos.reduce((total, item) => total + item.precio, 0);
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
      if (this.listItemRepartos.length > 0) {

        const reparto: Reparto = {
          anotacion: '',
          clave: this.clave.value ? this.clave.value : '',
          estado: 'P',
          id_cliente: this.cliente.id,
          items: this.listItemRepartos,
        }
        const res = await this.repartoService.insert(reparto)
        if (res) {
          this.router.navigate(['../'])
        }
      } else {
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
