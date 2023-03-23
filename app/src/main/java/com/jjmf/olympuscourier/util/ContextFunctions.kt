package com.jjmf.olympuscourier.util

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun Context.show(text:String?){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun String?.sinDatos() = this ?: "Sin datos"
fun String?.toMinusculas() : String {
    return try {
        if (this!=null && this.isNotEmpty()) {
            var cadena = ""
            this.split(" ").forEach {
                val palabra = it[0].uppercase()
                val sub = it.substring(1).lowercase()
                cadena = "$cadena$palabra$sub "
            }
            cadena
        }
        else this.toString()
    }catch (e:Exception){
        this.toString()
    }
}

fun Timestamp?.toFecha(): Fecha {
    val date = this?.toDate() ?: Date(System.currentTimeMillis())
    return Fecha(
        diaNum = "dd".toFormat(date),
        mes = "MM".toFormat(date),
        anio = "yyyy".toFormat(date),
        hora = "HH".toFormat(date),
        min = "mm".toFormat(date),
        dia = "EEEE".toFormat(date),
    )
}
fun Fecha?.format(patron:String = "dd/MM/yyyy") : String{
    return when(patron){
        "MM/yyyy" ->  "${this?.mes}/${this?.anio}"
        "dd/MM/yyyy" -> "${this?.diaNum}/${this?.mes}/${this?.anio}"
        else -> ""
    }
}
fun Fecha?.toMes() : String {
    return when(this?.mes){
        "01" -> "Enero"
        "02" -> "Febrero"
        "03" -> "Marzo"
        "04" -> "Abril"
        "05" -> "Mayo"
        "06" -> "Junio"
        "07" -> "Julio"
        "08" -> "Agosto"
        "09" -> "Setiembre"
        "10" -> "Octubre"
        "11" -> "Noviembre"
        "12" -> "Diciembre"
        else -> "Mes"
    }
}
data class Fecha(
    val diaNum: String = "dd".toFormat(Date(System.currentTimeMillis())),
    val mes: String = "MM".toFormat(Date(System.currentTimeMillis())),
    val anio: String = "yyyy".toFormat(Date(System.currentTimeMillis())),
    val hora: String = "HH".toFormat(Date(System.currentTimeMillis())),
    val min: String = "mm".toFormat(Date(System.currentTimeMillis())),
    val dia: String = "EEEE".toFormat(Date(System.currentTimeMillis())),
)
fun String.toFormat(date: Date) = SimpleDateFormat(this, Locale.getDefault()).format(date)
fun obtenerDireccion(context: Context, location: Location): Direccion {
    val geo = Geocoder(context)
    val list = geo.getFromLocation(location.latitude, location.longitude, 1)?.get(0)
    val adress = list?.getAddressLine(0) ?: ""
    val direc = adress.split(",")
    val dir = direc.first()
    val region = direc[1].split(" ")[1]
    return Direccion(
        calle = dir,
        region = region
    )
}
fun getFecha(format :String= "dd/MM/yyyy"):String{
    return SimpleDateFormat(format, Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toString()
}
data class Direccion(
    val calle: String,
    val region: String
)