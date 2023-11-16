import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Destino } from 'src/app/models/destino';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DestinosService {

  http = inject(HttpClient);

  listarDestinos(): Observable<Destino[]> {
    const url = `${environment.baseUrl}/api/destinos`;
    return this.http.get<any>(url);
  }
}
