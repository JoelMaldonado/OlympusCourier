package com.jjmf.android.olympuscourier.domain.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

data class Usuario(
    val id:String,
    val nombres:String,
    val apePaterno:String,
    val apeMaterno:String,
    val fechaNac:Timestamp,
    val celular:String,
    val correo:String,
    val rol:String,
    val doc:String,
    val clave:String
) {
    fun formatFecha(pattern:String = "dd/MM/yyyy") : String{
        return SimpleDateFormat(pattern, Locale.getDefault()).format(fechaNac.toDate())
    }
}
