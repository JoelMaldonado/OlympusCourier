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
    @SerializedName("fecha_nacimiento") val fecha_nacimiento:String?,
    @SerializedName("fecha_creacion") val fecha_creacion:String?,
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
            fecha_nacimiento = fecha_nacimiento ?: "1999-01-01T00:00:00.000Z",
        fecha_creacion = fecha_creacion ?: "1999-01-01T00:00:00.000Z",
        )
    }
}

data class LoginRequest(
    val documento:String,
    val clave:String
)