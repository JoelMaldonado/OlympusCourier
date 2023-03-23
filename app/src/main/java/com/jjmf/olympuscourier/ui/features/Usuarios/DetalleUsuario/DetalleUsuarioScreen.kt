package com.jjmf.olympuscourier.ui.features.Usuarios.DetalleUsuario

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.*
import com.jjmf.olympuscourier.util.show
import com.jjmf.olympuscourier.util.sinDatos

@Composable
fun DetalleUsuarioScreen(
    usuario: Usuario,
    back: () -> Unit,
    viewModel: DetalleUsuarioViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    if (viewModel.back){
        LaunchedEffect(key1 = Unit){
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
        Text(
            text = "Datos laborales",
            fontWeight = FontWeight.SemiBold,
            color = ColorP1,
            fontSize = 24.sp
        )
        CardFechaIngreso()
        DetalleItem(titulo = "Documento", descrip = usuario.documento.sinDatos())
        DetalleItem(titulo = "Nombres", descrip = usuario.nombres.sinDatos())
        DetalleItem(titulo = "Apellidos", descrip = "${usuario.apePaterno.sinDatos()} ${usuario.apeMaterno.sinDatos()}")
        DetalleItem(titulo = "Fecha Nac.", descrip = usuario.fechaNac.sinDatos())
        val rol =
            if (usuario.rol == "A") "Administrador" else if (usuario.rol == "U") "Usuario" else "Sin Rol"
        DetalleItem(titulo = "Tipo Usuario", descrip = rol)
        Text(
            text = "Contactar",
            fontWeight = FontWeight.SemiBold,
            color = ColorP1,
            fontSize = 24.sp
        )
        CardContactarTelf(titulo = "Teléfono", descrip = usuario.celular.sinDatos())

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
                            usuario.id?.let {id->
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
                if (viewModel.progres){
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }else{
                    Text(text = "Eliminar")
                }
            }
        }
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

@Composable
fun CardFechaIngreso() {
    Card(
        modifier = Modifier.padding(vertical = 5.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Fecha de ingreso",
                fontWeight = FontWeight.Medium,
                color = ColorT,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .background(ColorBox)
                    .padding(5.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "26/08/2022",
                    color = ColorT2,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun CardContactarTelf(titulo: String, descrip: String) {
    val context = LocalContext.current
    val callRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${descrip}"))
                context.startActivity(intent)
            } else {
                context.show("Debe aceptar los permisos")
            }
        }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Text(text = descrip, fontSize = 14.sp)
            }
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = {
                    val uri =
                        Uri.parse("https://api.whatsapp.com/send?phone=51${descrip}")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                },
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Whatsapp, contentDescription = null)
            }
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = {
                    if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        val intent =
                            Intent(Intent.ACTION_CALL, Uri.parse("tel:${descrip}"))
                        context.startActivity(intent)
                    } else {
                        callRequest.launch(Manifest.permission.CALL_PHONE)
                    }
                },
                backgroundColor = ColorP1.copy(),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun DetalleItem(titulo: String, descrip: String, color: Color = ColorT) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titulo,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier
                .weight(2f)
                .clip(RoundedCornerShape(10.dp))
                .background(ColorBox)
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = descrip, color = ColorT2, fontSize = 14.sp)
        }
    }
}
