package com.jjmf.olympuscourier.Data.Model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude

data class Conformidad(
    @get:Exclude var id:String? = null,
    val documento:String? = null,
    val fullName:String? = null,
    val direccion:String? = null,
    val latitud:Double? = null,
    val longitud:Double? = null,
    val celular:String? = null,
    val foto:String? = null,
    val time:Timestamp? = null
)
