package com.jjmf.android.olympuscourier.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.android.olympuscourier.ui.features.Login.LoginScreen
import com.jjmf.android.olympuscourier.ui.features.Menu.MenuScreen
import com.jjmf.android.olympuscourier.ui.features.VerRepartos.VerRepartosScreen

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Login.url){
        composable(Rutas.Login.url){
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.url)
                }
            )
        }
        composable(Rutas.Menu.url){
            MenuScreen(
                toVerRepartos = {
                    navController.navigate(Rutas.VerRepartos.url)
                }
            )
        }
        composable(Rutas.VerRepartos.url){
            VerRepartosScreen()
        }
    }
}