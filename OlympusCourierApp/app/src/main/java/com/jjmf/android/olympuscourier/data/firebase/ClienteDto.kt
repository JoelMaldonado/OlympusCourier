package com.jjmf.android.olympuscourier.data.firebase

import com.jjmf.android.olympuscourier.domain.model.Cliente

data class ClienteDto(
    var id: String? = null,
    val documento: String? = null,
    val nombres: String? = null,
    val apellidos: String? = null,
    val celular: String? = null,
){
    fun toDomain() : Cliente{
        return Cliente(
            id = id ?: "Sin ID",
            documento = documento ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            apellidos = apellidos ?: "Sin Valor",
            celular = celular ?: "Sin Celular"
        )
    }
}