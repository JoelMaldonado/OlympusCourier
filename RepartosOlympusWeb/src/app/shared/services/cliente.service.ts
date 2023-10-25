import { Injectable } from '@angular/core';
import { Firestore, collection, getDocs, getDoc, doc, query, where} from '@angular/fire/firestore';
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

  /*async searchCliente(data: string): Promise<Cliente[]> {
    const col = collection(this.fb, 'Cliente');
    const q = query(
      col,
      where('nombres', '>=', data),
      where('nombres', '<', data + '\uf8ff')
    );
    const result = await getDocs(q);
    const list : Cliente[] = []
    result.forEach(doc=>{
      const cliente = doc.data() as Cliente;
      list.push(cliente);
    })
    return list;
  }*/

  async searchCliente(data: string): Promise<Cliente[]> {
    const col = collection(this.fb, 'Cliente');
    const nameQuery = query(
      col,
      where('nombres', '>=', data),
      where('nombres', '<', data + '\uf8ff')
    );
  
    const docNumberQuery = query(
      col,
      where('documento', '>=', data),
      where('documento', '<', data + '\uf8ff')
    );
  
    const [nameResults, docNumberResults] = await Promise.all([getDocs(nameQuery), getDocs(docNumberQuery)]);
  
    const nameMatches: Cliente[] = [];
    nameResults.forEach((doc) => {
      const cliente = doc.data() as Cliente;
      nameMatches.push(cliente);
    });
  
    const docNumberMatches: Cliente[] = [];
    docNumberResults.forEach((doc) => {
      const cliente = doc.data() as Cliente;
      docNumberMatches.push(cliente);
    });
    const combinedResults = [...new Set([...nameMatches, ...docNumberMatches])];
  
    return combinedResults;
  }
}
