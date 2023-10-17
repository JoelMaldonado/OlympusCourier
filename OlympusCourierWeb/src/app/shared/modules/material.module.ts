import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatCardModule,
    MatSidenavModule,
    MatMenuModule,
    MatSelectModule,
    MatListModule,
    MatToolbarModule,
    MatDialogModule
  ],
  exports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatCardModule,
    MatSidenavModule,
    MatMenuModule,
    MatSelectModule,
    MatListModule,
    MatToolbarModule,
    MatDialogModule
  ]
})
export class MaterialModule { }
