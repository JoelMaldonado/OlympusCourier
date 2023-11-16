import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements AfterViewInit {
  animal: string = "";
  name: string = "Joel";

  listClientes = new MatTableDataSource<Cliente>();
  columnas: string[] = [
    'nombres',
    'tipo',
    'documento',
    'direc',
    'telf',
    'act',
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.listClientes.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'items por página';
  }

  constructor(
    private clienteService: ClienteService,
    public dialog: MatDialog
  ) {
    this.listarClientes()
  }

  async listarClientes() {
    this.clienteService.listarClientes().subscribe({
      next: data => {
        this.listClientes.data = data;
      },
      error: error => console.log(error)
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      data: { name: this.name, animal: this.animal },
      width: "950px"
    });

    dialogRef.afterClosed().subscribe(result => {
      this.animal = result;
    });
  }

  editCliente(item: Cliente) {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      data: item,
      width: "950px"
    })

    dialogRef.afterClosed().subscribe((data: Cliente) => {
      if (data) {
      }
    });

  }


}
