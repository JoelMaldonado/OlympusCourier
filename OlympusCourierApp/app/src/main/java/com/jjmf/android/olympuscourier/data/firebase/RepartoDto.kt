package com.jjmf.android.olympuscourier.data.firebase

import com.google.firebase.Timestamp
import com.jjmf.android.olympuscourier.domain.model.Cliente
import com.jjmf.android.olympuscourier.domain.model.Reparto

data class RepartoDto(
    var id: String? = null,
    val idCliente: String? = null,
    val distrito: String? = null,
    val direc: String? = null,
    val referencia: String? = null,
    val estado: String? = null,
    val total: Double? = null,
    val fecha: Timestamp? = null,
) {
    fun toDomain(
        cliente: Cliente?,
    ): Reparto {
        return Reparto(
            id = id ?: "Sin Valor",
            cliente = cliente ?: Cliente(
                id = "Sin Valor",
                documento = "Sin Valor",
                nombres = "Sin Valor",
                apellidos = "Sin Valor",
                celular = "Sin Valor"
            ),
            distrito = distrito ?: "Sin Valor",
            direc = direc ?: "Sin Valor",
            referencia = referencia ?: "Sin Valor",
            estado = estado ?: "Sin Valor",
            total = total ?: 0.0,
            fecha = fecha ?: Timestamp.now()
        )
    }
}
