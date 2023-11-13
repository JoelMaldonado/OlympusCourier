
import axios from 'axios';
const generarComprobante = (req, res) => {

    const {
        tipo_de_comprobante,
        serie,
        numero,
        cliente_tipo_de_documento,
        cliente_numero_de_documento,
        cliente_denominacion,
        cliente_direccion,
        cliente_email,
        total_gravada,
        total_igv,
        total,
        items
    } = req.data;

    const {
        unidad_de_medida,
        codigo_producto_sunat,
        descripcion,
        cantidad,
        valor_unitario,
        precio_unitario,
        descuento,
        subtotal,
        tipo_de_igv,
        igv,
        totalItem
    } = items

    const data = {
        "operacion": "generar_comprobante",
        "tipo_de_comprobante": 1,
        "serie": "FFF1",
        "numero": 2,
        "sunat_transaction": 1,
        "cliente_tipo_de_documento": 6,
        "cliente_numero_de_documento": "10744555189",
        "cliente_denominacion": "Maria Ronsangela",
        "cliente_direccion": "Pedro Moreno",
        "cliente_email": "tucliente@gmail.com",
        "cliente_email_1": "",
        "cliente_email_2": "",
        "fecha_de_emision": "03-11-2023",
        "fecha_de_vencimiento": "",
        "moneda": 1,
        "tipo_de_cambio": "",
        "porcentaje_de_igv": 18.00,
        "descuento_global": "",
        "total_descuento": "",
        "total_anticipo": "",
        "total_gravada": 600,
        "total_inafecta": "",
        "total_exonerada": "",
        "total_igv": 108,
        "total_gratuita": "",
        "total_otros_cargos": "",
        "total": 708,
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
                "valor_unitario": 500,
                "precio_unitario": 590,
                "descuento": "",
                "subtotal": 500,
                "tipo_de_igv": 1,
                "igv": 90,
                "total": 590,
                "anticipo_regularizacion": false,
                "anticipo_documento_serie": "",
                "anticipo_documento_numero": ""
            }
        ],
        "guias": [
        ],
        "venta_al_credito": [
        ]
    };

    const headers = {
        'Authorization': '7b3c966149524842b2af262bba5f6da0a783250cb5d946e29b37bef8abed1cd5',
        'Content-Type': 'application/json',
    };

    axios.post('https://api.nubefact.com/api/v1/c16ea3cf-bad1-4d36-a449-df085ee5aadb', data, { headers })
        .then(response => {
            res.json(response.data);
        })
        .catch(error => {
            if (error.response) {
                res.status(error.response.status).json(error.response.data);
            } else {
                res.status(500).json({ error: 'Hubo un error al realizar la solicitud' });
            }
        });
};

export default { generarComprobante }