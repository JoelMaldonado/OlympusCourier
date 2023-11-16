import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Firestore, collection, getDocs, getDoc, doc, query, where, addDoc, setDoc } from '@angular/fire/firestore';
import { Observable, catchError, throwError } from 'rxjs';
import { Cliente } from 'src/app/models/cliente';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  fb = inject(Firestore)
  http = inject(HttpClient)
  url = `${environment.baseUrl}/api/clientes`;

  addCliente1(cliente: Cliente) {
    return this.http.post(this.url, cliente)
  }

  async addCliente(cliente: Cliente): Promise<string | boolean> {
    try {
      const col = collection(this.fb, 'Cliente')
      const q = query(col, where('doc', '==', cliente.documento));
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


  exportClientes(listClientes: Cliente[]) {
    const call = this.http.post(this.url + '/exportarCliente', { listClientes }, { responseType: 'arraybuffer' })
    call.subscribe({
      next: (data: ArrayBuffer) => {
        const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'data_clientes.xlsx';
        link.click();
        window.URL.revokeObjectURL(link.href);
      }
    });
  }

  isLogged(): boolean {
    return localStorage.getItem('token') ? true : false;
  }

  async getClienteById(clienteId: string): Promise<Cliente> {
    const document = doc(this.fb, "Cliente", clienteId)
    return (await getDoc(document)).data() as Cliente
  }

  listarClientes(): Observable<Cliente[]> {
    return this.http.get<any>(this.url);
  }

  searchCliente(data: string): Observable<Cliente[]> {
    return this.http.get<any>(`${this.url}/search/${data}`)
  }

}
