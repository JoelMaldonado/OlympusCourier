package com.jjmf.olympuscourier.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Conformidad(
    @get:Exclude var id:String? = null,
    val codigo:Int? = null,
    val documento:String? = null,
    val nombres:String? = null,
    val apePaterno:String? = null,
    val apeMaterno:String? = null,
    val direccion:String? = null,
    val region:String? = null,
    val latitud:Double? = null,
    val longitud:Double? = null,
    val celular:String? = null,
    val costo:Double? = null,
    val foto:String? = null,
    val time:Timestamp? = null,
    val gasto:Boolean? = false,
    val detalleGasto:String? = null,
    val idUsuarioInsert:String? = null,
    val usuarioInsert:String? = null,
    val vigente:Boolean? = null,
    val idUsuarioDelete:String? = null,
    val usuarioDelete:String? = null,
    val fechaDelete:Timestamp? = null,
    val motivoDelete:String? = null
)
