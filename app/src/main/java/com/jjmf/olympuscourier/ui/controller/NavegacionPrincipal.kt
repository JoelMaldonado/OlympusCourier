package com.jjmf.olympuscourier.ui.controller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.olympuscourier.ui.features.AgregarPersona.AgregarPersonaScreen
import com.jjmf.olympuscourier.ui.features.ListadoPaquetes.ListadoPaquetesScreen
import com.jjmf.olympuscourier.ui.features.Login.LoginScreen
import com.jjmf.olympuscourier.ui.features.Mapa.MapaScreen
import com.jjmf.olympuscourier.ui.features.Menu.MenuScreen

@Composable
fun NavegacionPrincipal() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Login.route) {
        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.route)
                }
            )
        }
        composable(Rutas.Menu.route){
            MenuScreen(
                toMapa = {
                    navController.navigate(Rutas.Mapa.route)
                },
                toAgregarPersona = {
                                   navController.navigate(Rutas.AgregarPersona.route)
                },
                toListadoPaquetes = {
                    navController.navigate(Rutas.ListadoPaquetes.route)
                }
            )
        }
        composable(Rutas.AgregarPersona.route){
            AgregarPersonaScreen()
        }
        composable(Rutas.ListadoPaquetes.route){
            ListadoPaquetesScreen(
                toMapa = {
                    navController.navigate(Rutas.Mapa.route)
                }
            )
        }
        composable(Rutas.Mapa.route) {
            MapaScreen()
        }
    }
}