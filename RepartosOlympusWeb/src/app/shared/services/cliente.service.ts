import { Injectable } from '@angular/core';
import { Firestore, collection, getDocs, getDoc, doc } from '@angular/fire/firestore';
import { Cliente } from 'src/app/models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(
    private fb: Firestore
  ) {

  }

  async getClienteById(clienteId: string): Promise<Cliente> {
    const document = doc(this.fb, "Cliente", clienteId)
    return (await getDoc(document)).data() as Cliente
  }
  
  async listarClientes(): Promise<Cliente[]> {
    const col = collection(this.fb, 'Cliente');
    const allTodos = await getDocs(col);
    const list: Cliente[] = [];
  
    allTodos.docs.forEach((doc) => {
      const data = doc.data() as Cliente;
      list.push(data);
    });
    return list;
  }


}
