import { Injectable } from '@angular/core';
import { Firestore, addDoc, Timestamp } from '@angular/fire/firestore';
import { collection, getDocs } from 'firebase/firestore';
import { Reparto } from 'src/app/models/reparto';

@Injectable({
  providedIn: 'root'
})
export class RepartoService {


  constructor(
    private fb: Firestore
  ) {

  }

  async listarRepartos(): Promise<Reparto[]> {
    const col = collection(this.fb, 'Reparto')
    const allTodos = await getDocs(col);
    const destinos: Reparto[] = [];

    allTodos.docs.forEach((doc) => {
      const data = doc.data() as Reparto;
      destinos.push(data);
    });
    return destinos;
  }

  async insert(datos:any) {
    const col = collection(this.fb, 'Reparto')
    const reparto: Reparto = {
      cliente: {
        documento: datos.documento,
        nombres: datos.nombres,
        apellidos: datos.apellidos,
        celular: datos.celular,
      },
      total: parseInt(datos.total, 10) ?? 0,
      fecha: Timestamp.now(),
      estado: "Pendiente",
      distrito: datos.distrito,
      direc: datos.direc,
      referencia: datos.referencia,
      clave: datos.clave,
      anotacion: datos.anotaciones,
    }
    addDoc(col, reparto)
  }
}
