import { AfterViewInit, Component, ViewChild, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/models/cliente';
import { DialogAddClienteComponent } from 'src/app/shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-tabla-clientes',
  templateUrl: './tabla-clientes.component.html',
  styleUrls: ['./tabla-clientes.component.css']
})
export class TablaClientesComponent implements AfterViewInit {
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


  constructor(
    private clienteService: ClienteService
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

  ngAfterViewInit() {
    this.listClientes.paginator = this.paginator;
    this.paginator._intl.itemsPerPageLabel = 'items por página';
  }

  dialog = inject(MatDialog);


  openDialog(): void {
    const dialogRef = this.dialog.open(DialogAddClienteComponent, {
      width: "950px"
    });

    dialogRef.afterClosed().subscribe(result => {

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
