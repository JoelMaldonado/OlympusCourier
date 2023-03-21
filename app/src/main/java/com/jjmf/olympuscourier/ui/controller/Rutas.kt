package com.jjmf.olympuscourier.ui.controller

sealed class Rutas(val route: String) {
    object Login : Rutas(route = "Login")
    object Menu : Rutas(route = "Menu")
    object AgregarPersona : Rutas(route = "AgregarPersona")
    object MovimientosDiarios : Rutas(route = "MovimientosDiarios")
    object ConformidadEntrega : Rutas(route = "ConformidadEntrega")
    object Mapa : Rutas(route = "Mapa")
    object Ajusted : Rutas(route = "Ajustes")
}
