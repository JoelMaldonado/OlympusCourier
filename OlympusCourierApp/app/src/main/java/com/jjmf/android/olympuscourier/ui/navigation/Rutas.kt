package com.jjmf.android.olympuscourier.ui.navigation

sealed class Rutas(val url:String){
    object Login:Rutas(url = "login")
    object Menu:Rutas(url = "menu")
    object VerRepartos:Rutas(url = "ver_repartos")
    object DetailReparto:Rutas(url = "detail_reparto?{id}"){
        fun sendId(id:String) = "detail_reparto?$id"
    }

    object DatosPersonales:Rutas(url = "datos_personales")
}
