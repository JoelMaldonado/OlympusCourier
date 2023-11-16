package com.jjmf.android.olympuscourier.domain.model

import com.jjmf.android.olympuscourier.data.server.dto.ItemRepartoDto

data class Reparto(
    val id: Int,
    val anotacion: String,
    val clave: String,
    val estado: String,
    val fecha_creacion: String,
    val fecha_entrega: String,
    val id_cliente: Int,
    val cliente: Cliente?,
    val id_usuario: Int,
    val usuario: Usuario?,
    val id_repartidor: Int,
    val items: List<ItemRepartoDto>,
    val total: Double,
){
    fun formatFecha(pattern:String = "dd/MM/yyyy") : String{
        return fecha_creacion//SimpleDateFormat(pattern, Locale.getDefault()).format(fecha.toDate())
    }

    fun total(): Double {
        return items.sumOf { it.precio?.toDoubleOrNull() ?: 0.0 }
    }
}