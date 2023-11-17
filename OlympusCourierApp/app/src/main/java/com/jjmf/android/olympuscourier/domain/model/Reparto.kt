package com.jjmf.android.olympuscourier.domain.model

import com.jjmf.android.olympuscourier.data.server.dto.ItemRepartoDto
import java.text.SimpleDateFormat
import java.util.Locale

data class Reparto(
    val id: Int,
    val anotacion: String,
    val clave: String,
    val estado: String,
    val fecha_creacion: String,
    val fecha_entrega: String,
    val id_cliente: Int,
    val cliente: Cliente,
    val id_usuario: Int,
    val usuario: Usuario,
    val id_repartidor: Int,
    val items: List<ItemRepartoDto>,
    val total: Double,
){

    fun formatFecha(pattern: String = "dd/MM/yyyy"): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = inputFormat.parse(fecha_creacion)
        return if (date!=null){
            val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
            return outputFormat.format(date)
        }else "Sin Formato"
    }

    fun total(): Double {
        return items.sumOf { it.precio?.toDoubleOrNull() ?: 0.0 }
    }

    fun formatoID(): String {
        val idStr = id.toString().take(6).padStart(6, '0')
        return "#$idStr"
    }
}