package com.jjmf.android.olympuscourier.data.firebase

import com.google.firebase.Timestamp
import com.jjmf.android.olympuscourier.domain.model.Usuario

data class UsuarioDto(
    var id: String? = null,
    val nombres: String? = null,
    val apePaterno: String? = null,
    val apeMaterno: String? = null,
    val fechaNac: Timestamp? = null,
    val celular: String? = null,
    val correo: String? = null,
    val rol: String? = null,
    val doc: String? = null,
    val clave: String? = null,
) {
    fun toDomain(): Usuario {
        return Usuario(
            id = id ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            apePaterno = apePaterno ?: "Sin Valor",
            apeMaterno = apeMaterno ?: "Sin Valor",
            fechaNac = fechaNac ?: Timestamp.now(),
            celular = celular ?: "Sin Valor",
            correo = correo ?: "Sin Valor",
            rol = rol ?: "Sin Valor",
            doc = doc ?: "Sin Valor",
            clave = clave ?: "Sin Valor",
        )
    }
}