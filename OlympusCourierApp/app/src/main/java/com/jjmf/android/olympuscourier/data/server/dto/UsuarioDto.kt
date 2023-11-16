package com.jjmf.android.olympuscourier.data.server.dto

import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourier.domain.model.Usuario

data class UsuarioDto(
    @SerializedName("documento") val documento: String?,
    @SerializedName("nombres") val nombres: String?,
    @SerializedName("ape_materno") val ape_materno: String?,
    @SerializedName("ape_paterno") val ape_paterno: String?,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("rol") val rol: String?,
) {
    fun toDomain(): Usuario {
        return Usuario(
            documento = documento ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            ape_materno = ape_materno ?: "Sin Valor",
            ape_paterno = ape_paterno ?: "Sin Valor",
            telefono = telefono ?: "Sin Valor",
            correo = correo ?: "Sin Valor",
            rol = rol ?: "Sin Valor",
        )
    }
}