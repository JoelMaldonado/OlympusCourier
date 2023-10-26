import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent {
  animal: string = "";
  name: string = "Joel";

  listClientes: Cliente[] = [];

  constructor(
    private clienteService: ClienteService,
    public dialog: MatDialog
  ) {
    this.listarClientes()
  }

  async listarClientes() {
    this.listClientes = await this.clienteService.listarClientes();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      data: {name: this.name, animal: this.animal},
      width: "950px"
    });

    dialogRef.afterClosed().subscribe(result => {
      this.animal = result;
    });
  }
}
