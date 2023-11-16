package com.jjmf.android.olympuscourier.domain.model

data class Usuario(
    val documento:String,
    val nombres:String,
    val ape_materno:String,
    val ape_paterno:String,
    val telefono:String,
    val correo:String,
    val rol:String,
) {
    fun formatFecha(pattern:String = "dd/MM/yyyy") : String{
        return "sadsd"//SimpleDateFormat(pattern, Locale.getDefault()).format(fechaNac.toDate())
    }
}
