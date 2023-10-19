import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { RepartosComponent } from './components/repartos/repartos.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { DestinosComponent } from './components/destinos/destinos.component';
import { AgregarRepartoComponent } from './components/agregar-reparto/agregar-reparto.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'menu', component: MenuComponent,
    children: [
      { path: 'repartos', component: RepartosComponent },
      { path: 'clientes', component: ClientesComponent },
      { path: 'destinos', component: DestinosComponent },
      { path: 'agregar-reparto', component: AgregarRepartoComponent },
    ]
  },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
