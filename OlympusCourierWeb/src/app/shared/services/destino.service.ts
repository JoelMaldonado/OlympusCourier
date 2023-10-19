import { Injectable } from '@angular/core';
import { Firestore } from '@angular/fire/firestore';
import { collection, getDocs } from 'firebase/firestore';
import { Destino } from 'src/app/models/destino';


@Injectable({
  providedIn: 'root'
})
export class DestinoService {


  constructor(
    private fb: Firestore
  ) {

  }
  
  async listarUsuarios(): Promise<Destino[]> {
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
