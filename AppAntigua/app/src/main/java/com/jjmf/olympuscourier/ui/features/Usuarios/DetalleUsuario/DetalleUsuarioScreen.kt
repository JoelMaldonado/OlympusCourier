package com.jjmf.olympuscourier.ui.features.Usuarios.DetalleUsuario

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.CardFechaIngreso
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.CardLLamadaWsp
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.DetalleItem
import com.jjmf.olympuscourier.ui.theme.*
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.show
import com.jjmf.olympuscourier.util.sinDatos
import com.jjmf.olympuscourier.util.toFecha

@Composable
fun DetalleUsuarioScreen(
    usuario: Usuario,
    back: () -> Unit,
    viewModel: DetalleUsuarioViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }

    viewModel.mensaje?.let {
        context.show(it)
        viewModel.mensaje = null
    }
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
                text = "Datos laborales",
                fontWeight = FontWeight.SemiBold,
                color = ColorP1,
                fontSize = 22.sp
            )
            CardFechaIngreso(
                fecha = usuario.fechaIngreso?.toFecha().format()
            )
        }
        DetalleItem(titulo = "Documento", descrip = usuario.documento.sinDatos())
        DetalleItem(titulo = "Nombres", descrip = usuario.nombres.sinDatos())
        DetalleItem(
            titulo = "Apellidos",
            descrip = "${usuario.apePaterno.sinDatos()} ${usuario.apeMaterno.sinDatos()}"
        )
        DetalleItem(titulo = "Fecha Nac.", descrip = usuario.fechaNac.sinDatos())
        val rol =
            if (usuario.rol == "A") "Administrador" else if (usuario.rol == "U") "Usuario" else "Sin Rol"
        DetalleItem(titulo = "Tipo Usuario", descrip = rol)
        Text(
            text = "Contactar",
            fontWeight = FontWeight.SemiBold,
            color = ColorP1,
            fontSize = 22.sp
        )
        CardLLamadaWsp(titulo = "Teléfono", descrip = usuario.celular.sinDatos())

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Correo",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = ColorT
                )
                Text(text = usuario.correo.sinDatos(), fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (viewModel.loader) {
            CircularProgressIndicator(
                color = ColorP2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = "Reestablcer Contraseña",
                color = ColorP2,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        alertaReestabler(context) {
                            val newClave = "1234"
                            viewModel.reestablecerClave(usuario.copy(clave = newClave))
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = back,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorS1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Regresar")
            }
            Button(
                onClick = {
                    eliminarUsuario(
                        context = context,
                        click = {
                            usuario.id?.let { id ->
                                viewModel.delete(id)
                            }
                        }
                    )
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorR1,
                    contentColor = Color.White,
                    disabledBackgroundColor = ColorR1
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = !viewModel.progres
            ) {
                if (viewModel.progres) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}


private fun alertaReestabler(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(R.drawable.ic_recuperar)
        titleText = "Reestablecer Contraseña"
        contentText = "Se reseteara la contraseña del usuario, ¿Estas Seguro?"
        confirmButtonBackgroundColor = ColorP2.hashCode()
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

private fun eliminarUsuario(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(R.drawable.ic_delete)
        titleText = "Eliminar Usuario"
        contentText = "Al eliminar el usuario, ya no se podra volver a restaurar ¿Estas seguro?"
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