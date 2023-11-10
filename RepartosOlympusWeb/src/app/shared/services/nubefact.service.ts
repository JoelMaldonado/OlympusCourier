import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NubefactService {

  private apiUrl2 = 'api/v1/7f3465bbd8704739939217ab1860e5cb166c80cfc2b24c34ad5ad47a4f12a08f';
  private apiKey2 = 'eyJhbGciOiJIUzI1NiJ9.IjIxNWJmYmUzYzRkYzRmY2M4ZTczZDJjZWRmYWM5OTg5ZDVjZjg3ZjM4NzBlNDgxN2FjNjczYzdmMDBhNTA1ZTki.Js7qt-90BleBnXHbMiN7ioYE9T7nTq1xGDezjLBtAXs';


  constructor(
    private http: HttpClient
  ) {

  }

  generarComprobante(dat: any) {

    const total = dat.total
    const total_gravada = total / 1.18
    const total_igv = total - total_gravada

    const apiUrl = 'http://localhost:3000/nubefact';

    const urlPier = "https://api.pse.pe/api/v1/6baf3f2f6c284defa9cf148782cdb136f19c6f2ec1b84e8eb9f4144f67df2145"
    const tokenPier = "eyJhbGciOiJIUzI1NiJ9.ImMzYTljNmI5YWJlZTQ0ZDFiMjExZmRlMzIxNTE1ZDRhM2VkODFlMDQ1OTkyNDMyZDk3NTI2NjVjMDY2NDEzZGUi.oRgBsVpXqZlgJ1OPBQd0TpLEyeFrtWppa2vE92GjYA0"

    var today = new Date();
    var day = String(today.getDate()).padStart(2, '0');
    var month = String(today.getMonth() + 1).padStart(2, '0');
    var year = today.getFullYear();
    var fechaDeEmision = day + '-' + month + '-' + year;

    const data = {
      "operacion": "generar_comprobante",
      "tipo_de_comprobante": dat.tipoComp,
      "serie": (dat.tipoComp === 1) ? "F001" : (dat.tipoComp === 2) ? "B001" : null,
      "numero": 6,
      "sunat_transaction": 1,
      "cliente_tipo_de_documento": dat.tipoDoc,
      "cliente_numero_de_documento": dat.doc,
      "cliente_denominacion": dat.nombre,
      "cliente_direccion": dat.direc,
      "cliente_email": dat.correo,
      "cliente_email_1": "",
      "cliente_email_2": "",
      "fecha_de_emision": fechaDeEmision,
      "fecha_de_vencimiento": "",
      "moneda": 1,
      "tipo_de_cambio": "",
      "porcentaje_de_igv": 18.00,
      "descuento_global": "",
      "total_descuento": "",
      "total_anticipo": "",
      "total_gravada": total_gravada,
      "total_inafecta": "",
      "total_exonerada": "",
      "total_igv": total_igv,
      "total_gratuita": "",
      "total_otros_cargos": "",
      "total": total,
      "percepcion_tipo": "",
      "percepcion_base_imponible": "",
      "total_percepcion": "",
      "total_incluido_percepcion": "",
      "retencion_tipo": "",
      "retencion_base_imponible": "",
      "total_retencion": "",
      "total_impuestos_bolsas": "",
      "detraccion": false,
      "observaciones": "",
      "documento_que_se_modifica_tipo": "",
      "documento_que_se_modifica_serie": "",
      "documento_que_se_modifica_numero": "",
      "tipo_de_nota_de_credito": "",
      "tipo_de_nota_de_debito": "",
      "enviar_automaticamente_a_la_sunat": true,
      "enviar_automaticamente_al_cliente": false,
      "condiciones_de_pago": "",
      "medio_de_pago": "",
      "placa_vehiculo": "",
      "orden_compra_servicio": "",
      "formato_de_pdf": "",
      "generado_por_contingencia": "",
      "bienes_region_selva": "",
      "servicios_region_selva": "",
      "items": [
        {
          "unidad_de_medida": "NIU",
          "codigo": "001",
          "codigo_producto_sunat": "10000000",
          "descripcion": "DETALLE DEL PRODUCTO",
          "cantidad": 1,
          "valor_unitario": total_gravada,
          "precio_unitario": total,
          "descuento": "",
          "subtotal": total_gravada,
          "tipo_de_igv": 1,
          "igv": total_igv,
          "total": total,
          "anticipo_regularizacion": false,
          "anticipo_documento_serie": "",
          "anticipo_documento_numero": ""
        }
      ],
      "guias": [],
      "venta_al_credito": []
    }

    const body = { url: urlPier, data: data, token: tokenPier };

    return this.http.post(apiUrl, body);
  }

}
