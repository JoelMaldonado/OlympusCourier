package com.jjmf.android.olympuscourier.data.firebase

import com.google.firebase.Timestamp
import com.jjmf.android.olympuscourier.domain.model.Cliente
import com.jjmf.android.olympuscourier.domain.model.Reparto

data class RepartoDto(
    var id: String? = null,
    val idCliente: String? = null,
    val estado: String? = null,
    val fecha: Timestamp? = null,
    val clave: String? = null,
    val anotacion: String? = null,
    val items: List<ItemRepartoDto>? = null,
) {
    fun toDomain(
        cliente: Cliente?,
    ): Reparto {
        return Reparto(
            id = id ?: "Sin Valor",
            cliente = cliente ?: Cliente(
                id = "Sin Valor",
                doc = "Sin Valor",
                nombres = "Sin Valor",
                celular = "Sin Valor",
                distrito = "Sin Valor",
                direc = "Sin Valor",
                referencia = "Sin Valor",
            ),
            estado = estado ?: "Sin Valor",
            fecha = fecha ?: Timestamp.now(),
            clave = clave ?: "Sin Valor",
            anotacion = anotacion ?: "Sin Valor",
            items = items?.map { it.toDomain() } ?: emptyList()
        )
    }
}
