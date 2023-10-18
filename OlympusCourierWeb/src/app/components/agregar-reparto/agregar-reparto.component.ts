import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AgregarItemRepartoComponent } from './agregar-item-reparto/agregar-item-reparto.component';
import { ItemReparto } from './agregar-item-reparto/item-reparto';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { DestinoService } from 'src/app/shared/services/destino.service';
import { Destino } from 'src/app/models/destino';
import { map } from 'rxjs/operators';

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

  distritos : Destino[] = [
    {nombre: 'descrip',}
  ];

  @ViewChild(MatTable) table!: MatTable<ItemReparto>;
  
  constructor(
    public dialog: MatDialog,
    private destinoService:DestinoService
  ) {
    this.listarDestinos()
  }
  async listarDestinos() {
    const call = await (await this.destinoService.getAll()).pipe(
      map((destinos:any) => {
        return destinos.map((destino: any) => {
          return { nombre: destino.nombre } as Destino;
        });
      })
    ).toPromise();
  
    this.distritos = call;
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
