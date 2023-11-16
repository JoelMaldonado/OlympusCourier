package com.jjmf.android.olympuscourier.data.server.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.android.olympuscourier.domain.model.ItemReparto

data class ItemRepartoDto(
    @SerializedName("id") val id:Int?,
    @SerializedName("num_guia") val num_guia:String?,
    @SerializedName("detalle") val detalle:String?,
    @SerializedName("cant") val cant:Int?,
    @SerializedName("precio") val precio:String?,
    @SerializedName("id_reparto") val id_reparto:Int?,
    @SerializedName("id_tipo_paquete") val id_tipo_paquete:Int?,
) {
    fun toDomain(): ItemReparto {
        return ItemReparto(
            id = id ?: 0,
                    num_guia = num_guia ?: "Sin Data",
                    detalle = detalle ?: "Sin Data",
                    cant = cant ?: 0,
                    precio = precio ?: "Sin Data",
                    id_reparto = id_reparto ?: 0,
                    id_tipo_paquete = id_tipo_paquete ?: 0,
        )
    }
}
