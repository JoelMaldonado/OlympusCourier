import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { RepartosComponent } from './components/repartos/repartos.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { AgregarRepartoComponent } from './components/agregar-reparto/agregar-reparto.component';
import { ComprobanteComponent } from './components/comprobante/comprobante.component';
import { authGuard } from './shared/guards/auth.guard';
import { UsuariosComponent } from './components/usuarios/usuarios.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'menu', component: MenuComponent,
    children: [
      { path: 'repartos', component: RepartosComponent },
      { path: 'clientes', component: ClientesComponent },
      { path: 'comprobantes', component: ComprobanteComponent },
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'agregar-reparto', component: AgregarRepartoComponent },
    ],
    canActivate: [authGuard]
  },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
