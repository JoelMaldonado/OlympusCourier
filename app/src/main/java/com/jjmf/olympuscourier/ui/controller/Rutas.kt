package com.jjmf.olympuscourier.ui.controller

sealed class Rutas(val route: String) {
    object Login : Rutas(route = "Login")
    object Menu : Rutas(route = "Menu")
    object Usuarios : Rutas(route = "Usuarios")
    object DetalleUsuario : Rutas(route = "DetalleUsuario?{usuario}"){
        fun sendUsuario(usuario:String) = "DetalleUsuario?$usuario"
    }
    object MovimientosDiarios : Rutas(route = "MovimientosDiarios")
    object ReporteDiario : Rutas(route = "ReporteDiario")
    object ReporteGeneral : Rutas(route = "ReporteGeneral")
    object ConformidadEntrega : Rutas(route = "ConformidadEntrega")
    object RegistrarGasto : Rutas(route = "RegistrarGasto")
    object DetalleRegistro : Rutas(route = "DetalleRegistro?{registro}"){
        fun sendRegistro(registro:String) = "DetalleRegistro?${registro}"
    }
    object Mapa : Rutas(route = "Mapa")
    object Ajusted : Rutas(route = "Ajustes")
}
