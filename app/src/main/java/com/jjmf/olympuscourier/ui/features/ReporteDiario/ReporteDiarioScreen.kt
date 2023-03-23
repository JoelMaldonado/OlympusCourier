package com.jjmf.olympuscourier.ui.features.ReporteDiario

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.CardTotal
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardConformidad
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardEliminado
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardGasto
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.util.format
import com.jjmf.olympuscourier.util.toFecha
import com.jjmf.olympuscourier.util.toMes
import com.jjmf.olympuscourier.util.toMinusculas

@Composable
fun ReporteDiarioScreen(
    toDetalle: (Conformidad) -> Unit,
    viewModel: ReporteDiarioViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getList()
        viewModel.getListConformidad()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (viewModel.listadoMeses.isNotEmpty()) {
                        val fecha = viewModel.listadoMeses[viewModel.cont].time?.toFecha()
                        BigText(text = "${fecha.toMes()} ${fecha?.anio}")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            if (viewModel.cont < viewModel.listadoMeses.size - 1) {
                                viewModel.cont++
                                viewModel.mesSelected =
                                    viewModel.listadoMeses[viewModel.cont].time?.toFecha()
                                        .format("MM/yyyy")
                                viewModel.getListConformidadDias()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_left),
                            contentDescription = null,
                            tint = ColorP1,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            if (viewModel.cont > 0) {
                                viewModel.cont--
                                viewModel.mesSelected =
                                    viewModel.listadoMeses[viewModel.cont].time?.toFecha()
                                        .format("MM/yyyy")
                                viewModel.getListConformidadDias()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right),
                            contentDescription = null,
                            tint = ColorP1,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(viewModel.listadoDias.sortedBy { it.time }) {
                        ItemFecha(it)
                    }
                }
            }
            if (viewModel.progress) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = ColorP1,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            } else {
                items(viewModel.listado.sortedByDescending { it.codigo }) {
                    if (it.vigente == true) {
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
                    } else {
                        CardEliminado(
                            conformidad = it,
                            toDetalle = { toDetalle(it) }
                        )
                    }
                }
            }
        }
        val total = viewModel.listado.filter { it.vigente == true }.sumOf { it.costo ?: 0.0 }
        CardTotal(
            modifier = Modifier.align(Alignment.BottomCenter),
            total = total.toString()
        )
    }

}

@Composable
fun ItemFecha(conformidad: Conformidad, viewModel: ReporteDiarioViewModel = hiltViewModel()) {
    val format = conformidad.time?.toFecha().format()
    val color = if (format == viewModel.fechaSelected) ColorP1 else Color.Transparent
    val color2 = if (format == viewModel.fechaSelected) Color.White else Color.Black
    Column(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .clickable {
                viewModel.getListConformidad()
                viewModel.fechaSelected = format
            }
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val fecha = conformidad.time?.toFecha()
        Text(text = fecha?.dia.toString().toMinusculas().substring(0, 3), color = color2)
        Text(text = fecha?.diaNum.toString(), color = color2)
    }
}
