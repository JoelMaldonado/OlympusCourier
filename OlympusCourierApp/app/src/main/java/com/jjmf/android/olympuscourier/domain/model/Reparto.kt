package com.jjmf.android.olympuscourier.domain.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

data class Reparto(
    var id: String,
    val cliente: Cliente,
    val distrito: String,
    val direc: String,
    val referencia: String,
    val estado: String,
    val total: Double,
    val fecha: Timestamp,
){
    fun formatFecha(pattern:String = "dd/MM/yyyy") : String{
        return SimpleDateFormat(pattern, Locale.getDefault()).format(fecha.toDate())
    }
}