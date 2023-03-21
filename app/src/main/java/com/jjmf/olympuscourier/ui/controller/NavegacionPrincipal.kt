package com.jjmf.olympuscourier.ui.controller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.olympuscourier.ui.features.AgregarPersona.AgregarPersonaScreen
import com.jjmf.olympuscourier.ui.features.ConformidadEntrega.ConformidadEntregaScreen
import com.jjmf.olympuscourier.ui.features.Login.LoginScreen
import com.jjmf.olympuscourier.ui.features.Mapa.MapaScreen
import com.jjmf.olympuscourier.ui.features.Menu.MenuScreen
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.MovimientosDiariosScreen

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
        composable(Rutas.Menu.route) {
            MenuScreen(
                toMapa = {
                    navController.navigate(Rutas.Mapa.route)
                },
                toAgregarPersona = {
                    navController.navigate(Rutas.AgregarPersona.route)
                },
                toMovimientos = {
                    navController.navigate(Rutas.MovimientosDiarios.route)
                }
            )
        }
        composable(Rutas.AgregarPersona.route) {
            AgregarPersonaScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.MovimientosDiarios.route) {
            MovimientosDiariosScreen(
                toConformidad = {
                    navController.navigate(Rutas.ConformidadEntrega.route)
                }
            )
        }
        composable(Rutas.ConformidadEntrega.route){
            ConformidadEntregaScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.Mapa.route) {
            MapaScreen()
        }
    }
}