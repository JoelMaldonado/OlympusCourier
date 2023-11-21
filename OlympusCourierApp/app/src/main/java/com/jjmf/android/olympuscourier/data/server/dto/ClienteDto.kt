package com.jjmf.android.olympuscourier.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourier.domain.model.Cliente

data class ClienteDto(
    @SerializedName("tipo_doc") val tipo_doc: String?,
    @SerializedName("documento") val documento: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("genero") val genero: String?,
    @SerializedName("distrito_id") val distrito_id: Int?,
    @SerializedName("direc") val direc: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("url_maps") val url_maps: String?,
) {
    fun toDomain(): Cliente {
        return Cliente(
            tipo_doc = tipo_doc ?: "Sin Valor",
            documento = documento ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            telefono = telefono ?: "Sin Valor",
            correo = correo ?: "Sin Valor",
            genero = genero ?: "Sin Valor",
            distrito_id = distrito_id ?: 0,
            direc = direc ?: "Sin Valor",
            referencia = referencia ?: "Sin Valor",
            urlMaps = url_maps ?: "Sin Valor"
        )
    }
}