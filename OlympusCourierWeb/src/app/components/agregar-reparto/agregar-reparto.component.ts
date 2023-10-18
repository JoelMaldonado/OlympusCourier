import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AgregarItemRepartoComponent } from './agregar-item-reparto/agregar-item-reparto.component';
import { ItemReparto } from './agregar-item-reparto/item-reparto';
import { MatTable, MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-agregar-reparto',
  templateUrl: './agregar-reparto.component.html',
  styleUrls: ['./agregar-reparto.component.css']
})
export class AgregarRepartoComponent {
  document: string = '';
  clientName: string = '';
  clientCelular: string = '';
  district: string = '';
  address: string = '';
  reference: string = '';
  password: string = '';
  notes: string = '';
  displayedColumns: string[] = ['descrip', 'precio', 'cant', 'actions'];
  dataSource : ItemReparto[]= [];

  @ViewChild(MatTable) table!: MatTable<ItemReparto>;
  
  constructor(
    public dialog: MatDialog
  ) {
  }

  submitOrder() {

  }

  remove(itemEliminar:any){
    this.dataSource = this.dataSource.filter(item => item !== itemEliminar)
  }

  openProductDialog() {
    const dialogRef = this.dialog.open(AgregarItemRepartoComponent, {
      width: '800px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.dataSource.push(result)
      this.table.renderRows();
    });
  }
}
