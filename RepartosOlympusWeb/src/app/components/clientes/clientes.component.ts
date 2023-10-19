import { Component } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent {

  listClientes: Cliente[] = [];

  constructor(
    private clienteService: ClienteService
  ) {
    this.listarClientes()
  }

  async listarClientes() {
    this.listClientes = await this.clienteService.listarClientes();
  }

}
