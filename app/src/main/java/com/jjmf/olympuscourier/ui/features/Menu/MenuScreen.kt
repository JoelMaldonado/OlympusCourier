package com.jjmf.olympuscourier.ui.features.Menu

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.zxing.integration.android.IntentIntegrator
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorP2
import com.jjmf.olympuscourier.util.show

@Composable
fun MenuScreen(
    toMapa: () -> Unit,
    toAgregarPersona: () -> Unit,
    toMovimientos: () -> Unit,
) {
    val context = LocalContext.current

    val scanResult = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)
        if (result != null) {
            if (result.contents != null) {
                context.show(result.contents)
                toMapa()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
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
            Text(text = "Hola Jeanpier,", color = ColorP1, fontWeight = FontWeight.SemiBold)
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
                    res = R.drawable.ic_add_user,
                    text = "Agregar\nUsuarios",
                    click = {
                        toAgregarPersona()
                    }
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
                    text = "Reporte\nGeneral",
                    click = {

                    }
                )
                CardHome(
                    modifier = Modifier.weight(1f),
                    res = R.drawable.ic_ajuste,
                    text = "Establecer\nPreferencias",
                    click = {

                    }
                )
            }
        }
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