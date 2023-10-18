import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { RepartosComponent } from './components/repartos/repartos.component';
import { DrawerComponent } from './shared/components/drawer/drawer.component';
import { MaterialModule } from './shared/modules/material.module';
import { SharedModule } from './shared/modules/shared.module';
import { FormsModule } from '@angular/forms';
import { ClientesComponent } from './components/clientes/clientes.component';
import { DestinosComponent } from './components/destinos/destinos.component';
import { AgregarRepartoComponent } from './components/agregar-reparto/agregar-reparto.component';
import { AgregarItemRepartoComponent } from './components/agregar-reparto/agregar-item-reparto/agregar-item-reparto.component';
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { getFirestore, provideFirestore } from '@angular/fire/firestore';
import { provideStorage,getStorage } from '@angular/fire/storage';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    RepartosComponent,
    DrawerComponent,
    ClientesComponent,
    DestinosComponent,
    AgregarRepartoComponent,
    AgregarItemRepartoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    SharedModule,
    FormsModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage())
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
