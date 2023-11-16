package com.jjmf.android.olympuscourier.domain.model

data class Cliente(
    val tipo_doc:String,
    val documento:String,
    val nombres:String,
    val telefono:String,
    val correo:String,
    val genero:String,
    val distrito_id:Int,
    val direc:String,
    val referencia:String,
)
