import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { debounceTime } from 'rxjs';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-buscar-cliente-reparto',
  templateUrl: './buscar-cliente-reparto.component.html',
  styleUrls: ['./buscar-cliente-reparto.component.css']
})
export class BuscarClienteRepartoComponent{

  @Output() clienteEmit = new EventEmitter<Cliente | undefined>();
  cliente: Cliente | undefined;

  documento = new FormControl('')
  data$: Cliente[] = [];
  showSugerencias = false;
  isLoading = false;

  constructor() {
    this.documento.valueChanges.pipe(
      debounceTime(1000)
    ).subscribe(() => {
      this.buscarCliente();
    });
  }

  clienteService = inject(ClienteService)
  dialog = inject(MatDialog)

  buscarCliente() {
    if (!this.documento.value) {
      return;
    }
    if(this.cliente){
      return;
    }
    this.isLoading = true;
    this.showSugerencias = true;
    this.clienteService.searchCliente(this.documento.value).subscribe({
      next: data => {
        this.data$ = data;
        this.isLoading = false;
      },
      error: error => {
        console.log(error)
        this.isLoading = false;
      }
    });
  }

  borrarCliente() {
    this.cliente = undefined;
    this.documento.setValue('');
    this.clienteEmit.emit(undefined);
  }

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

  selectCliente(item: Cliente) {
    this.cliente = item;
    this.documento.setValue(item.nombres ? item.nombres : this.documento.value)
    this.showSugerencias = false
    this.data$ = []
    this.clienteEmit.emit(item);
  }
}
