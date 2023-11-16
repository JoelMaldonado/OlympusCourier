import { Component, EventEmitter, Output, ViewChild, inject } from '@angular/core';
import { ItemReparto } from '../../agregar-reparto.component';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { DialogAddItemRepartoComponent } from 'src/app/shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-tabla-items-reparto',
  templateUrl: './tabla-items-reparto.component.html',
  styleUrls: ['./tabla-items-reparto.component.css']
})
export class TablaItemsRepartoComponent {

  columnas: string[] = [
    'guia',
    'tipo',
    'descrip',
    'cant',
    'precio',
    'act',
  ];

  listItemRepartos: ItemReparto[] = [];
  @Output() listEmit = new EventEmitter<ItemReparto[]>();

  @ViewChild(MatTable) table!: MatTable<ItemReparto>;


  /** Añadir Item Reparto*/
  openDialogAddItemReparto() {
    const dialogRef = this.dialog.open(DialogAddItemRepartoComponent, {
      width: "770px"
    });

    dialogRef.afterClosed().subscribe((data: ItemReparto) => {
      if (data) {
        this.listItemRepartos.push(data)
        this.table.renderRows();
        this.listEmit.emit(this.listItemRepartos)
      }
    });
  }

  dialog = inject(MatDialog)


  editItemReparto(item: ItemReparto) {
    const dialogRef = this.dialog.open(DialogAddItemRepartoComponent, {
      data: item,
      width: "770px"
    })

    dialogRef.afterClosed().subscribe((data: ItemReparto) => {
      if (data) {
        const index = this.listItemRepartos.findIndex((element) => element === item);
        if (index !== -1) {
          this.listItemRepartos[index] = data;
          this.table.renderRows();
          this.listEmit.emit(this.listItemRepartos)
        }
      }
    });
  }

  deleteItemReparto(item: ItemReparto) {
    Swal.fire({
      title: 'Eliminar Item',
      text: '¿Estas seguro?',
      icon: 'warning',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Eliminar',
      showCancelButton: true,
      confirmButtonColor: '#047CC4'
    }).then((res) => {
      if (res.isConfirmed) {
        const index = this.listItemRepartos.indexOf(item);
        if (index !== -1) {
          this.listItemRepartos.splice(index, 1);
          this.table.renderRows();
          this.listEmit.emit(this.listItemRepartos)
        }
      }
    })
  }
}
