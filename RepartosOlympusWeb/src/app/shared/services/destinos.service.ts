import { Injectable } from '@angular/core';
import { Firestore, collection, getDocs } from '@angular/fire/firestore';
import { Destino } from 'src/app/models/destino';

@Injectable({
  providedIn: 'root'
})
export class DestinosService {

  constructor(
    private fb: Firestore
  ) {

  }
  
  async listarDestinos(): Promise<Destino[]> {
    const col = collection(this.fb, 'Destino');
    const allTodos = await getDocs(col);
    const destinos: Destino[] = [];
  
    allTodos.docs.forEach((doc) => {
      const data = doc.data() as Destino;
      destinos.push(data);
    });
    return destinos;
  }


}
