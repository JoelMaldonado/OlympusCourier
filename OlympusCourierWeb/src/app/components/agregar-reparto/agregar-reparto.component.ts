import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AgregarItemRepartoComponent } from './agregar-item-reparto/agregar-item-reparto.component';
import { ItemReparto } from './agregar-item-reparto/item-reparto';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-agregar-reparto',
  templateUrl: './agregar-reparto.component.html',
  styleUrls: ['./agregar-reparto.component.css']
})
export class AgregarRepartoComponent {
  document: string = '';
  clientName: string = '';
  district: string = '';
  address: string = '';
  reference: string = '';
  password: string = '';
  notes: string = '';
  displayedColumns: string[] = ['descrip', 'precio', 'cant'];


  constructor(
    public dialog: MatDialog
  ) {
  }

  submitOrder() {

  }

  listReparto = new MatTableDataSource<ItemReparto>();

  openProductDialog() {
    this.listReparto.data.push({numGuia: "sad", descrip: "Marron", precio: "5", cant: "10", tipo: "Paqueteria"})
    const dialogRef = this.dialog.open(AgregarItemRepartoComponent, {
      width: '800px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      let a: ItemReparto = result;
      this.listReparto.data.push(a);
      console.log(this.listReparto.data);
    });
  }
}
