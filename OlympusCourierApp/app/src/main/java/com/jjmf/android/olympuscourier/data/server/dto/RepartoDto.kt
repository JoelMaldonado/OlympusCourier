package com.jjmf.android.olympuscourier.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourier.domain.model.Cliente
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.domain.model.Usuario

data class RepartoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("anotacion") val anotacion: String?,
    @SerializedName("clave") val clave: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("fecha_creacion") val fecha_creacion: String?,
    @SerializedName("fecha_entrega") val fecha_entrega: String?,
    @SerializedName("id_cliente") val id_cliente: Int?,
    @SerializedName("cliente") val cliente: ClienteDto?,
    @SerializedName("id_usuario") val id_usuario: Int?,
    @SerializedName("usuario") val usuario: UsuarioDto?,
    @SerializedName("id_repartidor") val id_repartidor: Int?,
    @SerializedName("items") val items: List<ItemRepartoDto>?,
    @SerializedName("total") val total: Double?,
) {
    fun toDomain(): Reparto {
        return Reparto(
            id = id ?: 0,
            anotacion = anotacion ?: "Sin Data",
            clave = clave ?: "Sin Data",
            estado = estado ?: "Sin Data",
            fecha_creacion = fecha_creacion ?: "Sin Data",
            fecha_entrega = fecha_entrega ?: "Sin Data",
            id_cliente = id_cliente ?: 0,
            cliente = cliente?.toDomain() ?: Cliente(
                tipo_doc = "Sin Data",
                documento = "Sin Data",
                nombres = "Sin Data",
                telefono = "Sin Data",
                correo = "Sin Data",
                genero = "Sin Data",
                distrito_id = 0,
                direc = "Sin Data",
                referencia = "Sin Data",
            ),
            id_usuario = id_usuario ?: 0,
            usuario = usuario?.toDomain() ?: Usuario(
                documento = "Sin Valor",
                nombres = "Sin Valor",
                ape_materno = "Sin Valor",
                ape_paterno = "Sin Valor",
                telefono = "Sin Valor",
                correo = "Sin Valor",
                rol = "Sin Valor",
                fecha_nacimiento = "Sin Valor",
                fecha_creacion = "Sin Valor",
            ),
            id_repartidor = id_repartidor ?: 0,
            items = items ?: emptyList(),
            total = total ?: 0.0,
        )
    }
}
