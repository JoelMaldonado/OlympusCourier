package com.jjmf.olympuscourier.ui.controller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.olympuscourier.ui.features.Login.LoginScreen
import com.jjmf.olympuscourier.ui.features.Menu.MenuScreen
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.MovimientosDiariosScreen
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.screens.ConformidadEntrega.ConformidadEntregaScreen
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.screens.RegistrarGasto.RegistrarGastoScreen
import com.jjmf.olympuscourier.ui.features.Perfil.PerfilScreen
import com.jjmf.olympuscourier.ui.features.ReporteDiario.ReporteDiarioScreen
import com.jjmf.olympuscourier.ui.features.ReporteDiario.features.DetalleRegistro.DetalleRegistroScreen
import com.jjmf.olympuscourier.ui.features.Usuarios.DetalleUsuario.DetalleUsuarioScreen
import com.jjmf.olympuscourier.ui.features.Usuarios.UsuariosScreen
import com.jjmf.olympuscourier.ui.screen.SplashScreen

@Composable
fun NavegacionPrincipal() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Splash.route) {
        composable(Rutas.Splash.route){
            SplashScreen {
                navController.popBackStack()
                navController.navigate(Rutas.Login.route)
            }
        }
        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.route)
                },
                toMovimiento = {
                    navController.navigate(Rutas.MovimientosDiarios.route)
                }
            )
        }
        composable(Rutas.Menu.route) {
            MenuScreen(
                toUsuarios = {
                    navController.navigate(Rutas.Usuarios.route)
                },
                toMovimientos = {
                    navController.navigate(Rutas.MovimientosDiarios.route)
                },
                logout = {
                    prefs.clearUser()
                    navController.backQueue.clear()
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                },
                toReporte = {
                    navController.navigate(Rutas.ReporteDiario.route)
                },
                toPerfil = {
                    navController.navigate(Rutas.Perfil.route)
                }
            )
        }
        composable(Rutas.Usuarios.route) {
            UsuariosScreen(
                toDetalleUsuario = {
                    val usuario = Gson().toJson(it)
                    navController.navigate(Rutas.DetalleUsuario.sendUsuario(usuario))
                }
            )
        }
        composable(Rutas.DetalleUsuario.route){
            val usuario = it.arguments?.getString("usuario")
            if (usuario != null) Gson().fromJson(usuario, Usuario::class.java)?.let {user->
                DetalleUsuarioScreen(
                    usuario = user,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(Rutas.MovimientosDiarios.route) {
            MovimientosDiariosScreen(
                toConformidad = {
                    navController.navigate(Rutas.ConformidadEntrega.route)
                },
                toRegistrarGasto = {
                    navController.navigate(Rutas.RegistrarGasto.route)
                },
                toLogin = {
                    prefs.clearUser()
                    navController.backQueue.clear()
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                },
                toDetalle = {
                    val registro = Gson().toJson(it)
                    navController.navigate(Rutas.DetalleRegistro.sendRegistro(registro))
                }
            )
        }
        composable(Rutas.ConformidadEntrega.route) {
            ConformidadEntregaScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.RegistrarGasto.route) {
            RegistrarGastoScreen(
                back = {
                    navController.popBackStack()
                }
            )
        }
        composable(Rutas.ReporteDiario.route){
            ReporteDiarioScreen(
                toDetalle = {
                    val registro = Gson().toJson(it)
                    navController.navigate(Rutas.DetalleRegistro.sendRegistro(registro))
                }
            )
        }
        composable(Rutas.DetalleRegistro.route) {
            val registro = it.arguments?.getString("registro")
            if (registro != null) Gson().fromJson(registro, Conformidad::class.java)?.let {conformidad->
                DetalleRegistroScreen(
                    conformidad = conformidad,
                    back = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(Rutas.Perfil.route){
            PerfilScreen(
                logout = {
                    prefs.clearUser()
                    navController.backQueue.clear()
                    navController.popBackStack()
                    navController.navigate(Rutas.Login.route)
                }
            )
        }
    }
}