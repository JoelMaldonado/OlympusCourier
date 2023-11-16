import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ComprobantesService {

  http = inject(HttpClient)

  generarComprobante(value: any) {
    
  }


  saveComprobante(data: any) {

  }


}
