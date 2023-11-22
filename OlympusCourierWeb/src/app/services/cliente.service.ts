import { Injectable, inject } from '@angular/core';
import { Cliente } from '../models/cliente';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  http = inject(HttpClient);
  url = `${environment.baseUrl}/api/clientes`;

  searchCliente(data: string): Observable<Cliente[]> {
    return this.http.get<any>(`${this.url}/search/${data}`)
  }

  constructor() { }

  isLogged(): boolean {
    return localStorage.getItem('token') ? true : false;
  }
}
