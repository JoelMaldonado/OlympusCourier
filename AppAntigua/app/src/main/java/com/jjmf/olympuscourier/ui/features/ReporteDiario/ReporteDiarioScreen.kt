package com.jjmf.olympuscourier.ui.features.ReporteDiario

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.components.TotalCard
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardConformidad
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardEliminado
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardGasto
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.util.Fecha
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.toMes
import com.jjmf.olympuscourier.util.toMinusculas
import kotlin.math.absoluteValue

@Composable
fun ReporteDiarioScreen(
    toDetalle: (Conformidad) -> Unit,
    viewModel: ReporteDiarioViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit){
        viewModel.getListConformidad()
    }
    val context = LocalContext.current
    viewModel.mensajeError?.let {
        alertaError(context, it){
            viewModel.mensajeError = null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorP1)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        if (viewModel.cont > 0) {
                            viewModel.cont--
                            viewModel.getListDias()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }
                if (viewModel.listMeses.isNotEmpty()) {
                    val fecha = viewModel.listMeses[viewModel.cont]
                    BigText(text = "${fecha.toMes()} ${fecha.anio}", color = Color.White)
                }
                IconButton(
                    onClick = {
                        if (viewModel.cont < viewModel.listMeses.size - 1) {
                            viewModel.cont++
                            viewModel.getListDias()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item { Spacer(modifier = Modifier.width(20.dp)) }
                items(viewModel.listDias) {
                    ItemFecha(it)
                }
                item { Spacer(modifier = Modifier.width(20.dp)) }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(viewModel.listConformidad.sortedByDescending { it.codigo }) {
                    if (it.vigente == false) {
                        CardEliminado(
                            conformidad = it,
                            toDetalle = { toDetalle(it) }
                        )
                    } else {
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
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

            val total = viewModel.listConformidad.filter { it.vigente == true }

            TotalCard(
                icon = R.drawable.ic_dinero,
                text = "Ingresos",
                monto = total.filter { it.gasto == false }.sumOf { it.costo?.absoluteValue ?: 0.0 }.toString(),
                color = ColorP1
            )
            TotalCard(
                icon = R.drawable.ic_decrease,
                text = "Gastos",
                monto = total.filter { it.gasto == true }.sumOf { it.costo?.absoluteValue ?: 0.0 }.toString(),
                color = ColorR1
            )
        }
    }

}

fun alertaError(context: Context, it: String, function: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).apply {
        titleText = "¡Opss!"
        contentText = it
        setConfirmButton("Confirmar"){
            function()
            dismissWithAnimation()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        show()
    }
}

@Composable
fun ItemFecha(fecha: Fecha, viewModel: ReporteDiarioViewModel = hiltViewModel()) {
    val format = fecha.format()
    val color = if (format == viewModel.fechaSelected) ColorP1 else Color.Transparent
    val color2 = if (format == viewModel.fechaSelected) Color.White else Color.Black
    Column(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .clickable {
                viewModel.fechaSelected = format
                viewModel.getListConformidad()
            }
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = fecha.dia.toMinusculas().substring(0, 3), color = color2)
        Text(text = fecha.diaNum, color = color2)
    }
}
