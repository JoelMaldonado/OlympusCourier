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
import { ReactiveFormsModule } from '@angular/forms';
import { AgregarItemRepartoComponent } from './components/agregar-reparto/agregar-item-reparto/agregar-item-reparto.component';
import { HttpClientModule } from '@angular/common/http';
import { DrawerComponent } from './shared/components/drawer/drawer.component';

@NgModule({
  declarations: [
    AppComponent,
    AgregarRepartoComponent,
    ClientesComponent,
    DestinosComponent,
    LoginComponent,
    MenuComponent,
    RepartosComponent,
    AgregarItemRepartoComponent,
    DrawerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage()),
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
