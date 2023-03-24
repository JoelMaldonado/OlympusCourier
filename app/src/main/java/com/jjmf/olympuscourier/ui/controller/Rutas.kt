package com.jjmf.olympuscourier.ui.controller

sealed class Rutas(val route: String) {
    object Splash : Rutas(route = "Splash")
    object Login : Rutas(route = "Login")
    object Menu : Rutas(route = "Menu")
    object Usuarios : Rutas(route = "Usuarios")
    object DetalleUsuario : Rutas(route = "DetalleUsuario?{usuario}"){
        fun sendUsuario(usuario:String) = "DetalleUsuario?$usuario"
    }
    object MovimientosDiarios : Rutas(route = "MovimientosDiarios")
    object ReporteDiario : Rutas(route = "Perfil")
    object Perfil : Rutas(route = "ReporteDiario")
    object ConformidadEntrega : Rutas(route = "ConformidadEntrega")
    object RegistrarGasto : Rutas(route = "RegistrarGasto")
    object DetalleRegistro : Rutas(route = "DetalleRegistro?{registro}"){
        fun sendRegistro(registro:String) = "DetalleRegistro?${registro}"
    }
}
