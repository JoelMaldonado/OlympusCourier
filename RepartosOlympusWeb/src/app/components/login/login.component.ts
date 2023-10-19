import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formulario: FormGroup;
  
  constructor(
    private router: Router,
    private fb: FormBuilder
  ) {

    this.formulario = this.fb.group({
      user: [''],
      clave: ['']
    })
  }

  onSubmit() {
    this.router.navigateByUrl('menu/repartos')
  }

}
