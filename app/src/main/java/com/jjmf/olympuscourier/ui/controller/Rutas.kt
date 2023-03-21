package com.jjmf.olympuscourier.ui.controller

sealed class Rutas(val route: String) {
    object Login : Rutas(route = "Login")
    object Menu : Rutas(route = "Menu")
    object AgregarPersona : Rutas(route = "AgregarPersona")
    object Mapa : Rutas(route = "Mapa")
    object ListadoPaquetes : Rutas(route = "ListadoPaquetes")
    object Ajusted : Rutas(route = "Ajustes")
}
