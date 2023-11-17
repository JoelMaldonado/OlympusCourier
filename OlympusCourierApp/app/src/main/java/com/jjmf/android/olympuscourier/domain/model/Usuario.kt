package com.jjmf.android.olympuscourier.domain.model

import java.text.SimpleDateFormat
import java.util.Locale

data class Usuario(
    val documento:String,
    val nombres:String,
    val ape_materno:String,
    val ape_paterno:String,
    val telefono:String,
    val correo:String,
    val rol:String,
    val fecha_nacimiento:String,
    val fecha_creacion:String,
) {
    fun formatFecha(pattern: String = "dd/MM/yyyy"): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = inputFormat.parse(fecha_nacimiento)
        return if (date!=null){
            val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
            return outputFormat.format(date)
        }else "Sin Formato"
    }

    fun formatTelefono(): String {
        return telefono.chunked(3).joinToString(" ")
    }
}
