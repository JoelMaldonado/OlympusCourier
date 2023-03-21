package com.jjmf.olympuscourier.Data.Model

import com.google.firebase.firestore.Exclude

data class Usuario(
    @get:Exclude var id:String? = null,
    val documento:String? = null,
    val clave:String? = null,
    val nombres:String? = null,
    val apellidos:String? = null,
    val fechaNac:String? = null,
    val celular:String? = null,
    val rol:String? = null
)
