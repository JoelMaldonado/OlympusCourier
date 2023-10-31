package com.jjmf.android.olympuscourier.data.firebase

import com.google.firebase.Timestamp
import com.jjmf.android.olympuscourier.domain.model.Cliente

data class ClienteDto(
    var id: String? = null,
    val doc: String? = null,
    val nombres: String? = null,
    val celular: String? = null,
    val distrito:String? = null,
    val direc:String? = null,
    val ref:String? = null,
){
    fun toDomain() : Cliente{
        return Cliente(
            id = id ?: "Sin ID",
            doc = doc ?: "Sin Valor",
            nombres = nombres ?: "Sin Valor",
            celular = celular ?: "Sin Celular",
            distrito = distrito ?: "Sin Valor",
            direc = direc ?: "Sin Valor",
            referencia = ref ?: "Sin Valor"
        )
    }
}