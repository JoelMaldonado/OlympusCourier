import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formulario: FormGroup;
  clienteService = inject(ClienteService);

  constructor(
    private router: Router,
    private fb: FormBuilder
  ) {

    if (this.clienteService) {
      this.router.navigate(['/menu', 'repartos'])
    }

    this.formulario = this.fb.group({
      user: [''],
      clave: ['']
    })
  }

  onSubmit() {
    localStorage.setItem('token', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKb2VsIE1hbGRvbmFkbyIsImlhdCI6MTY5ODI3MjU4OSwiZXhwIjpudWxsLCJhdWQiOiIiLCJzdWIiOiIiLCJOb21icmUiOiJKb2VsIE1hbGRvbmFkbyIsIkNsYXZlIjoiUGVycml0b0NhbGllbnRlIiwiY29ycmVvIjoiam1hbGRvbmFkb0BnbWFpbC5jb20iLCJyb2wiOiJBZG1pbiJ9.HESojbMmEdqtvky5aR0j7Bi06s76qQ-NdGlcJps9kwg')
    this.router.navigate(['/menu', 'repartos'])
  }

}
