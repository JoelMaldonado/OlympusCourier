/**Componentes**/
import { AppComponent } from './app.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { AgregarRepartoComponent } from './components/agregar-reparto/agregar-reparto.component';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { RepartosComponent } from './components/repartos/repartos.component';
import { ComprobanteComponent } from './components/comprobante/comprobante.component';
import { GenerarBoletaComponent } from './components/comprobante/generar-boleta/generar-boleta.component';

/**Shared Components**/
import { DrawerComponent } from './shared/components/drawer/drawer.component';
import { DialogAddClienteComponent } from './shared/components/dialog-add-cliente/dialog-add-cliente.component';
import { CajaTextoComponent } from './shared/components/caja-texto/caja-texto.component';
import { DialogAddItemRepartoComponent } from './shared/components/dialog-add-item-reparto/dialog-add-item-reparto.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';

/**Modulos**/
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './shared/modules/material.module';

/**Firebase**/
import { environment } from '../environments/environment';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { provideFirestore, getFirestore } from '@angular/fire/firestore';
import { provideStorage, getStorage } from '@angular/fire/storage';
import { UsuariosComponent } from './components/usuarios/usuarios.component';

@NgModule({
  declarations: [
    AppComponent,
    AgregarRepartoComponent,
    ClientesComponent,
    LoginComponent,
    MenuComponent,
    RepartosComponent,
    DrawerComponent,
    ComprobanteComponent,
    GenerarBoletaComponent,
    DialogAddClienteComponent,
    CajaTextoComponent,
    DialogAddItemRepartoComponent,
    NavbarComponent,
    UsuariosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage()),
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
