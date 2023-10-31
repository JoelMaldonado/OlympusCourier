package com.jjmf.android.olympuscourier.data.firebase

import com.jjmf.android.olympuscourier.domain.model.ItemReparto

data class ItemRepartoDto(
    val cant: Int? = null,
    val cat: String? = null,
    val descrip: String? = null,
    val nGuia: String? = null,
    val precio: Double? = null,
) {
    fun toDomain(): ItemReparto {
        return ItemReparto(
            cant = cant ?: 0,
            cat = cat ?: "Sin Valor",
            descrip = descrip ?: "Sin Valor",
            nGuia = nGuia ?: "Sin Valor",
            precio = precio ?: 0.0,
        )
    }
}
