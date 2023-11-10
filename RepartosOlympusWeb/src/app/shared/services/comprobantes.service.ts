import { Injectable } from '@angular/core';
import { Firestore, addDoc, Timestamp, collection, getDocs } from '@angular/fire/firestore';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ComprobantesService {

  constructor(
    private fb: Firestore
  ) {

  }

  async saveComprobante(data: any): Promise<boolean> {
    try {
      const col = collection(this.fb, 'Comprobantes')
      await addDoc(col, data);
      return true;
    } catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Error al insertar',
        text: 'Hubo un problema al insertar el documento. Por favor, inténtelo de nuevo más tarde.',

      })
      return false;
    }
  }


}
