package com.jjmf.android.olympuscourier.ui.navigation

sealed class Rutas(val url:String){
    object Login:Rutas(url = "login")
    object Menu:Rutas(url = "menu")
    object VerRepartos:Rutas(url = "ver_repartos")
}
