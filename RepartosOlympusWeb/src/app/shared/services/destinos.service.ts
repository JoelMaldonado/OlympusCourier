import { Injectable } from '@angular/core';
import { Firestore, collection, getDocs } from '@angular/fire/firestore';
import { Destino } from 'src/app/models/destino';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class DestinosService {

  constructor(
    private fb: Firestore
  ) {

  }
  
  async listarDestinos(): Promise<Destino[]> {
    try {
    
    const col = collection(this.fb, 'Destino');
    const allTodos = await getDocs(col);
    const destinos: Destino[] = [];
  
    allTodos.docs.forEach((doc) => {
      const data = doc.data() as Destino;
      destinos.push(data);
    });
    return destinos;
    } catch (error) {
      console.log(error);
      Swal.fire({
        icon: 'error',
        title: 'Error al obtener',
        text: 'Hubo un problema al obtener los destinos. Por favor, inténtelo de nuevo más tarde.',
      })
      return [];
    }
  }


}
