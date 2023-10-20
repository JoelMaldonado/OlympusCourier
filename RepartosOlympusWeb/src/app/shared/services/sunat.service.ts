import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SunatService {

  constructor(
    private http: HttpClient
  ) {
  }


  async listarDocumentos(): Promise<any> {
    const params = new HttpParams()
      .set('personaId', '6452caf8d24fdd00141dbc91')
      .set('personaToken', 'DEV_c3Ry2tpNshsO8l7pN7VygnD3OjmgRuDY2L5QfyTzWKwrvbiqRSzvK2Oh4MyXktGC');
    const response = await this.http.get<any>(`${environment.baseUrl}documents/getAll`, { params })
      .pipe(
        map(data => data)
      )
      .toPromise();
    return response;
  }

  getPdf(id: any, tipo:any, fileName:string) {
    const url = `${environment.baseUrl}documents/${id}/getPDF/${tipo}/${fileName}.pdf`;
    this.http.get(url, { responseType: 'arraybuffer' }).subscribe((data: ArrayBuffer) => {
      const blob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      window.open(url);
    });
  }
}
