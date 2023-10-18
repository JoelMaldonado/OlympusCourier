import { Injectable } from '@angular/core';
import { Firestore, collectionData } from '@angular/fire/firestore';
import { collection, getDocs } from 'firebase/firestore';
import { Destino } from 'src/app/models/destino';


@Injectable({
  providedIn: 'root'
})
export class DestinoService {


  constructor(
    private firestore: Firestore
  ) {
  }

 async getAll() {
  let col = collection(this.firestore, 'Destino');
  return await collectionData(col)
}


}
