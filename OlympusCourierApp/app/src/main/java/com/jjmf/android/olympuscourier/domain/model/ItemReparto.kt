package com.jjmf.android.olympuscourier.domain.model

import com.google.gson.annotations.SerializedName

data class ItemReparto(
    val id:Int,
    val num_guia:String,
    val detalle:String,
    val cant:Int,
    val precio:String,
    val id_reparto:Int,
    val id_tipo_paquete:Int,
)
