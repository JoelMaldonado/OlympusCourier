package com.jjmf.olympuscourier.ui.features.Perfil

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.CardFechaIngreso
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.DetalleItem
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.sinDatos
import com.jjmf.olympuscourier.util.toFecha

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = hiltViewModel(),
    logout:()->Unit
) {

    if (viewModel.logout){
        LaunchedEffect(key1 = Unit){
            logout()
            viewModel.logout = false
        }
    }

    val usuario = prefs.getUser()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mis Datos laborales",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorP1,
                    fontSize = 22.sp
                )
                CardFechaIngreso(
                    fecha = usuario?.fechaIngreso?.toFecha().format()
                )
            }
            DetalleItem(titulo = "Documento", descrip = usuario?.documento.sinDatos())
            DetalleItem(titulo = "Nombres", descrip = usuario?.nombres.sinDatos())
            DetalleItem(
                titulo = "Apellidos",
                descrip = "${usuario?.apePaterno.sinDatos()} ${usuario?.apeMaterno.sinDatos()}"
            )
            DetalleItem(titulo = "Fecha Nac.", descrip = usuario?.fechaNac.sinDatos())
            val rol =
                if (usuario?.rol == "A") "Administrador" else if (usuario?.rol == "U") "Usuario" else "Sin Rol"
            DetalleItem(titulo = "Tipo Usuario", descrip = rol)
            DetalleItem(titulo = "Celular", descrip = usuario?.celular.sinDatos())
            DetalleItem(titulo = "Correo", descrip = usuario?.correo.sinDatos())
            Spacer(modifier = Modifier.weight(1f))
            if (viewModel.loader){
                CircularProgressIndicator(
                    color = ColorR1,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 10.dp)
                )
            }else{
                Text(
                    text = "Eliminar Cuenta",
                    color = ColorR1,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)
                        .clickable {
                            alertaEliminarCuenta(context) {
                                usuario?.id?.let { id ->
                                    viewModel.eliminarCuenta(id)
                                }
                            }
                        }
                )
            }
        }
    }

}

private fun alertaEliminarCuenta(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(com.jjmf.olympuscourier.R.drawable.ic_delete)
        titleText = "Eliminar Cuenta"
        contentText = "¿Estas seguro de querer eliminar tu cuenta? Si aceptas, se perderan todos tus datos"
        confirmButtonBackgroundColor = ColorR1.hashCode()
        cancelButtonBackgroundColor = ColorS1.hashCode()
        setConfirmButton("Confirmar") {
            dismissWithAnimation()
            click()
        }
        setCancelButton("Cancelar") {
            dismissWithAnimation()
        }
        show()
    }
}
