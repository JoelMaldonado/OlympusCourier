import { Injectable } from '@angular/core';
import { Firestore, collectionData } from '@angular/fire/firestore';
import { collection, getDocs, getFirestore } from 'firebase/firestore';
@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  
  db = getFirestore();
  colRef = collection(this.db, 'Destino');

  constructor(
    private firestore: Firestore
  ) {
  }

  autenticado: boolean = false;

  async login(usuario: string, clave: string): Promise<number> {
    try {
      let loginExitoso = 1;
      const querySnapshot = await getDocs(this.colRef);
      querySnapshot.forEach(doc => {
        const data: any = doc.data();
        if (data.usuario === usuario && data.clave === clave) {
          console.log('Inicio de sesión exitoso');
          loginExitoso = 2;
          this.autenticado = true;
        } else {
          console.log('No se pudo iniciar Sesion');
        }
      });
      return loginExitoso;
    } catch (error) {
      return 3
    }
  }

  getCredencial() {
    let col = collection(this.firestore, 'Credencial');
    return collectionData(col)
  }
}
