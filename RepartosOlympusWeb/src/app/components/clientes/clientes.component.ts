import { Component, inject } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent {
  name: string = "Joel";

  listClientes: Cliente[] = []

  clienteService = inject(ClienteService);

  constructor() {
    this.clienteService.listarClientes().subscribe({
      next: data => this.listClientes = data
    })
  }

  exportar() {
    this.clienteService.exportClientes(this.listClientes);
  }

}
