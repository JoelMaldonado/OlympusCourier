import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatStepperModule } from '@angular/material/stepper';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatTooltipModule,
    MatDialogModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatStepperModule
  ],
  exports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatTableModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatTooltipModule,
    MatDialogModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatStepperModule
  ]
})
export class MaterialModule { }
