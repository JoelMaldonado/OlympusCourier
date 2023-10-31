package com.jjmf.android.olympuscourier.ui.features.Menu

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorS1

@Composable
fun MenuScreen(
    toVerRepartos: () -> Unit,
    toDatosPersonales: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    val context = LocalContext.current

    if (viewModel.usuario == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    val usuario = viewModel.usuario ?: return

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_large_slogan),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Hola ${usuario.nombres}",
                color = ColorP1,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Qué haremos hoy!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = ColorP1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Tus opciones", color = ColorP1, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_usuarios,
                    text = "Listado\nUsuarios",
                    click = {}
                )
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_diario,
                    text = "Repartos\nDiarios",
                    click = toVerRepartos
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_reporte,
                    text = "Reporte\nDiario",
                    click = {}
                )
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_perfil,
                    text = "Datos\nPersonales",
                    click = toDatosPersonales
                )
            }
        }
        IconButton(
            onClick = {
                alertaCerrarSesion(context, click = {})
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                tint = ColorP1,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = "Version 1.0",
            color = Color.Gray.copy(0.5f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(5.dp)
        )
    }

    BackHandler {
        alertaCerrarSesion(
            context = context,
            click = {}
        )
    }
}

fun alerta(context: Context) {
    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
        titleText = "Sin Registros"
        contentText = "Realizar primero movimientos"
        setConfirmButton("Confirmar") {
            dismissWithAnimation()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        show()
    }
}

fun alertaCerrarSesion(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(com.jjmf.android.olympuscourier.R.drawable.ic_logout)
        titleText = "Cerrar Sesión"
        contentText = "¿Esta seguro de querer terminar la sesión?"
        setConfirmButton("Confirmar") {
            click()
            dismissWithAnimation()
        }
        setCancelButton("Cancelar") {
            dismissWithAnimation()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        cancelButtonBackgroundColor = ColorS1.hashCode()
        show()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHome(
    modifier: Modifier,
    @DrawableRes res: Int,
    text: String,
    click: () -> Unit,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorP1
        ),
        onClick = {
            click()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color.White,
                border = BorderStroke(2.dp, color = ColorP2)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = res),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Text(
                text = text,
                modifier = Modifier.align(Alignment.End),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}