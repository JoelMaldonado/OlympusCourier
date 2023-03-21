package com.jjmf.olympuscourier.ui.features.MovimientosDiarios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components.CardConformidad
import com.jjmf.olympuscourier.ui.theme.ColorP1

@Composable
fun MovimientosDiariosScreen(
    toConformidad : ()->Unit,
    viewModel: MovimientosDiariosViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit){
        viewModel.getList()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        item {
            //RowFechas()
            BigText(
                text = "Movimientos Diarios",
                modifier = Modifier.padding(top = 20.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Conformidades de entrega:", color = ColorP1, fontWeight = FontWeight.SemiBold)
                FloatingActionButton(
                    onClick = toConformidad,
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
        items(viewModel.listado) {
            CardConformidad(conformidad = it)
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
