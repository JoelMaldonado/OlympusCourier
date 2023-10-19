import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideFirestore, getFirestore } from '@angular/fire/firestore';
import { provideStorage, getStorage } from '@angular/fire/storage';
import { AgregarRepartoComponent } from './components/agregar-reparto/agregar-reparto.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { DestinosComponent } from './components/destinos/destinos.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { RepartosComponent } from './components/repartos/repartos.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DrawerComponent } from './shared/components/drawer/drawer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './shared/modules/material.module';
import { ComprobanteComponent } from './components/comprobante/comprobante.component';
import { GenerarBoletaComponent } from './components/comprobante/generar-boleta/generar-boleta.component';
import { DialogAddClienteComponent } from './shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { CajaTextoComponent } from './shared/components/caja-texto/caja-texto.component';
import { DialogAddItemRepartoComponent } from './shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';

@NgModule({
  declarations: [
    AppComponent,
    AgregarRepartoComponent,
    ClientesComponent,
    DestinosComponent,
    LoginComponent,
    MenuComponent,
    RepartosComponent,
    DrawerComponent,
    ComprobanteComponent,
    GenerarBoletaComponent,
    DialogAddClienteComponent,
    CajaTextoComponent,
    DialogAddItemRepartoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage()),
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
