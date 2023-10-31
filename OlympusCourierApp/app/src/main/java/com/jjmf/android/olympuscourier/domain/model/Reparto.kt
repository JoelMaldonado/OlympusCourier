package com.jjmf.android.olympuscourier.domain.model

import com.google.firebase.Timestamp
import com.jjmf.android.olympuscourier.data.firebase.ItemRepartoDto
import java.text.SimpleDateFormat
import java.util.Locale

data class Reparto(
    var id: String,
    val cliente: Cliente,
    val estado: String,
    val fecha: Timestamp,
    val clave:String,
    val anotacion:String,
    val items: List<ItemReparto>
){
    fun formatFecha(pattern:String = "dd/MM/yyyy") : String{
        return SimpleDateFormat(pattern, Locale.getDefault()).format(fecha.toDate())
    }

    fun total(): Double {
        return items.sumOf { it.cant * it.precio }
    }
}