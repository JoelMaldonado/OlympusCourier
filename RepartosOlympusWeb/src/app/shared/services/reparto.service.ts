import { Injectable } from '@angular/core';
import { Firestore, addDoc, Timestamp, collection, getDocs } from '@angular/fire/firestore';
import { Reparto } from 'src/app/models/reparto';
import { ClienteService } from './cliente.service';

@Injectable({
  providedIn: 'root'
})
export class RepartoService {

  constructor(
    private fb: Firestore,
    private clienteService:ClienteService
  ) {

  }

  async listarRepartos(): Promise<Reparto[]> {
    const col = collection(this.fb, 'Reparto')
    const allTodos = await getDocs(col);
    const destinos: Reparto[] = [];

    allTodos.docs.forEach(async doc => {
      const data : any = doc.data();
      const reparto : Reparto = {
        cliente: await this.clienteService.getClienteById(data.idCliente),
        total: data.total,
        fecha: data.fecha,
        estado: data.estado,
        distrito: data.distrito,
        direc: data.direc,
        referencia: data.referencia,
        clave: data.clave,
        anotacion: data.anotacion,
      }
      destinos.push(reparto);
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
