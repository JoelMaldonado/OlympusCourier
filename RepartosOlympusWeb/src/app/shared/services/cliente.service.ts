import { Injectable, inject } from '@angular/core';
import { Firestore, collection, getDocs, getDoc, doc, query, where, addDoc, setDoc } from '@angular/fire/firestore';
import { Cliente } from 'src/app/models/cliente';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {


  /**
   * getDocs() => Obtiene la información una sola vez
   * collectionData() => Obtiene la información en tiempo real
   * **/

  fb = inject(Firestore)

  async addCliente(cliente: Cliente): Promise<string | boolean> {
    try {
      const col = collection(this.fb, 'Cliente')
      const q = query(col, where('doc', '==', cliente.doc));
      const querySnapshot = await getDocs(q);

      if (querySnapshot.empty) {
        const doc = await addDoc(col, cliente);
        return doc.id;
      } else {
        console.error('El cliente con este documento ya está registrado');
        Swal.fire({
          icon: 'error',
          title: 'Documento ya registrado',
          text: 'El cliente con este documento ya está registrado en nuestra base de datos.',
          confirmButtonColor: '#05ACD7',
        });
        return false;
      }
    } catch (error) {
      console.error('Error al insertar el cliente:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error al insertar el cliente',
        text: 'Hubo un problema al guardar los datos del cliente. Por favor, inténtalo de nuevo.',
        confirmButtonColor: '#05ACD7',
      });
      return false;
    }
  }

  async editCliente(cliente: Cliente): Promise<boolean> {
    try {
      const col = collection(this.fb, 'Cliente');
      const clienteDoc = doc(col, cliente.id);
      await setDoc(clienteDoc, cliente);
      return true;
    } catch (error) {
      console.error('Error al editar el cliente:', error);
      return false;
    }
  }

  isLogged(): boolean {
    return localStorage.getItem('token') ? true : false;
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

  async searchCliente(data: string): Promise<Cliente[]> {
    const col = collection(this.fb, 'Cliente');
    const nameQuery = query(
      col,
      where('nombres', '>=', data),
      where('nombres', '<', data + '\uf8ff')
    );

    const docNumberQuery = query(
      col,
      where('doc', '>=', data),
      where('doc', '<', data + '\uf8ff')
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
