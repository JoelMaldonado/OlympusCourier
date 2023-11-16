package com.jjmf.android.olympuscourier.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jjmf.android.olympuscourier.ui.features.DetalleReparto.DetailRepartoScreen
import com.jjmf.android.olympuscourier.ui.features.Login.LoginScreen
import com.jjmf.android.olympuscourier.ui.features.Menu.MenuScreen
import com.jjmf.android.olympuscourier.ui.features.Perfil.PerfilScreen

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Login.url) {
        composable(Rutas.Login.url) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.url)
                }
            )
        }
        composable(Rutas.Menu.url) {
            MenuScreen(
                toPerfil = {
                    navController.navigate(Rutas.Perfil.url)
                },
                toDetalle = {
                    navController.navigate(Rutas.DetailReparto.sendId(it))
                }
            )
        }
        composable(Rutas.Perfil.url) {
            PerfilScreen()
        }
        composable(
            route = Rutas.DetailReparto.url,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("id")?.let { id ->
                DetailRepartoScreen(
                    idReparto = id,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}