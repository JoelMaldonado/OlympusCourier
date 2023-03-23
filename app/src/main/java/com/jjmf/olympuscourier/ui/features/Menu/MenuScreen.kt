package com.jjmf.olympuscourier.ui.features.Menu

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorP2
import com.jjmf.olympuscourier.ui.theme.ColorS1

@Composable
fun MenuScreen(
    toUsuarios: () -> Unit,
    toMovimientos: () -> Unit,
    toReporte: () -> Unit,
    logout: () -> Unit,
    toGeneral:()->Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        IconButton(
            onClick = {
                alertaCerrarSesion(context, click = logout)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_large),
                contentDescription = null,
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Hola ${prefs.getUser()?.nombres},",
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
                    click = toUsuarios
                )
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_add_producto,
                    text = "Movimientos\nDiarios",
                    click = toMovimientos
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_historial,
                    text = "Reporte\nDiario",
                    click = toReporte
                )
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_grafico,
                    text = "Reporte\nGeneral",
                    click = toGeneral
                )
            }
        }
    }
    BackHandler {
        alertaCerrarSesion(
            context = context,
            click = logout
        )
    }
}

fun alertaCerrarSesion(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(R.drawable.ic_logout)
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardHome(
    modifier: Modifier,
    @DrawableRes res: Int,
    text: String,
    click: () -> Unit
) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        backgroundColor = ColorP1,
        onClick = click
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