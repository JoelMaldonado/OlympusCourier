package com.jjmf.olympuscourier.util

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.Timestamp
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.ColorP1
import java.text.SimpleDateFormat
import java.util.*

fun Context.show(text:String?){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun String?.sinDatos() = if (this != null && this.isNotEmpty() )this else "Sin datos"
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

fun hayInternet(context: Context, event:()->Unit) {
    if (context.red()) event()
    else{
        SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
            setCustomImage(R.drawable.ic_sin_internet)
            titleText = "Sin Conexion a internet"
            contentText = "Asegurate de estar conectado a una red Wi-Fi o Red de datos"
            setConfirmButton("Confirmar"){
                dismissWithAnimation()
            }
            confirmButtonBackgroundColor = ColorP1.hashCode()
            show()
        }
    }
}
fun Context.red():Boolean{
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
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
        date = date
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
    val diaNum: String,
    val mes: String,
    val anio: String,
    val hora: String,
    val min: String,
    val dia: String,
    val date: Date
)
fun String.toFormat(date: Date) = SimpleDateFormat(this, Locale.getDefault()).format(date)
fun obtenerDireccion(context: Context, location: Location): Direccion? {
    return try {
        val geo = Geocoder(context)
        val list = geo.getFromLocation(location.latitude, location.longitude, 1)?.get(0)
        val adress = list?.getAddressLine(0) ?: ""
        val direc = adress.split(",")
        val dir = direc.first()
        val region = direc[1].split(" ")[1]
        Direccion(
            calle = dir,
            region = region
        )
    }catch (e:Exception){
        null
    }
}


fun alertaPrecio(context:Context, click:()->Unit) {
    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
        titleText = "Importe Mínimo"
        contentText = "El importe ingresado debe ser mayor a S/1"
        setConfirmButton("Confirmar"){
            click()
            dismissWithAnimation()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        show()
    }
}
fun String.isNumeric(): Boolean {
    return this.all { char -> char.isDigit() }
}
fun getFecha(format :String= "dd/MM/yyyy"):String{
    return SimpleDateFormat(format, Locale.getDefault())
        .format(Date(System.currentTimeMillis())).toString()
}
data class Direccion(
    val calle: String,
    val region: String
)