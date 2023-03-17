package com.jjmf.olympuscourier.ui.controller

sealed class Rutas(val route: String) {

    object Login : Rutas(route = "Login")
}
