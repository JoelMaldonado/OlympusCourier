package com.jjmf.android.olympuscourier.ui.navigation

sealed class Rutas(val url:String){
    object Login:Rutas(url = "login")
    object Menu:Rutas(url = "menu")
    object VerRepartos:Rutas(url = "ver_repartos")
    object DetailReparto:Rutas(url = "detail_reparto?{id}"){
        fun sendId(id:Int) = "detail_reparto?$id"
    }
    object DarConformidad:Rutas(url = "dar_conformidad?{id}"){
        fun sendId(id:Int) = "dar_conformidad?$id"
    }
object Perfil:Rutas(url = "perfil")
    object DatosPersonales:Rutas(url = "datos_personales")
}
