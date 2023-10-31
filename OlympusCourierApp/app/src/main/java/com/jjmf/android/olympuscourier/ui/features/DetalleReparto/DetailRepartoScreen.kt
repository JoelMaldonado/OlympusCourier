package com.jjmf.android.olympuscourier.ui.features.DetalleReparto

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.jjmf.android.olympuscourier.ui.components.AlertaInsertarClave
import com.jjmf.android.olympuscourier.ui.components.DetalleItem
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.util.show

@Composable
fun DetailRepartoScreen(
    idReparto: String,
    back: () -> Unit,
    viewModel: DetailRepartoViewModel = hiltViewModel(),
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.getReparto(idReparto)
    }

    val context = LocalContext.current

    val reparto = viewModel.reparto ?: return


    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            viewModel.back = false
            back()
        }
    }

    if (viewModel.reparto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }


    if (viewModel.alertDarConformidad) {
        AlertaInsertarClave(
            clave = reparto.clave,
            close = {
                viewModel.alertDarConformidad = false
            },
            ok = {
                viewModel.darConformidad(reparto)
            }
        )
    }


    viewModel.error?.let {
        viewModel.error = null
        context.show(it)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(
                    text = "CODIGO #",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorT,
                    fontSize = 22.sp
                )
            }
            DetalleItem(titulo = "Documento", descrip = reparto.cliente.doc)
            DetalleItem(titulo = "Cliente", descrip = reparto.cliente.nombres)
            DetalleItem(titulo = "Celular", descrip = reparto.cliente.celular)
            DetalleItem(titulo = "Distrito", descrip = reparto.cliente.distrito)
            DetalleItem(titulo = "Dirección", descrip = reparto.cliente.direc)
            DetalleItem(titulo = "Referencia", descrip = reparto.cliente.referencia)
            DetalleItem(titulo = "Fecha", descrip = reparto.formatFecha())
            DetalleItem(titulo = "Total", descrip = "S/${reparto.total()}")
            DetalleItem(titulo = "Estado", descrip = reparto.estado)


            LazyColumn(modifier = Modifier.weight(1f)){
                items(reparto.items){
                    Text(text = it.descrip)
                }
            }

            Button(
                onClick = {
                    viewModel.alertDarConformidad = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorP2,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Dar Conformidad")
            }
        }
    }
}