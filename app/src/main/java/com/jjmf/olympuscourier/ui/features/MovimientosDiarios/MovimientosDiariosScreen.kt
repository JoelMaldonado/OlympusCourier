package com.jjmf.olympuscourier.ui.features.MovimientosDiarios

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
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
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.features.Menu.alertaCerrarSesion
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardConformidad
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardGasto
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.util.getFecha
import com.jjmf.olympuscourier.util.show

@Composable
fun MovimientosDiariosScreen(
    toConformidad: () -> Unit,
    toRegistrarGasto: () -> Unit,
    toLogin: () -> Unit,
    toDetalle:(Conformidad)->Unit,
    viewModel: MovimientosDiariosViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.getList()
    }

    if (prefs.getUser()?.rol != "A") {
        BackHandler {
            alertaCerrarSesion(context) {
                toLogin()
            }
        }
    }
    val mapRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) toConformidad()
            else {
                context.show("Debes Aceptar los permisos")
            }
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                    Text(text = getFecha("dd/MM/yyyy"), color = Color.Gray.copy(0.5f))
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = toRegistrarGasto,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorR1,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reducir),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Registrar Gasto",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }
                BigText(
                    text = "Movimientos Diarios"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Conformidades de entrega:",
                        color = ColorP1,
                        fontWeight = FontWeight.SemiBold
                    )
                    FloatingActionButton(
                        onClick = {
                            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                toConformidad()
                            } else {
                                mapRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }
                        },
                        backgroundColor = ColorP1,
                        contentColor = Color.White
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
            items(viewModel.listado.sortedByDescending { it.codigo }) {
                if (it.gasto == true) {
                    CardGasto(
                        conformidad = it,
                        toDetalle = { toDetalle(it) }
                    )
                } else {
                    CardConformidad(
                        conformidad = it,
                        toDetalle = { toDetalle(it) }
                    )
                }
            }
        }
        val total = viewModel.listado.sumOf { it.costo ?: 0.0 }
        CardTotal(
            modifier = Modifier.align(Alignment.BottomCenter),
            total = total.toString()
        )
    }
}

@Composable
fun CardTotal(
    modifier: Modifier,
    total: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = ColorP1,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dinero),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Total del Día:", color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "S/$total", color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun RowFechas() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
        Text(text = "18/03/2023", color = Color.Gray.copy(0.5f))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Buscar fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorP1,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
        }
    }
}
