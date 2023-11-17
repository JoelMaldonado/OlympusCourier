package com.jjmf.android.olympuscourier.ui.features.DetalleReparto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.ui.components.AlertaInsertarClave
import com.jjmf.android.olympuscourier.ui.components.DetalleItem
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.ui.theme.ColorWsp
import com.jjmf.android.olympuscourier.util.show

@Composable
fun DetailRepartoScreen(
    idReparto: Int,
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(
                    text = "Visualizar Reparto",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorT,
                    fontSize = 22.sp
                )
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = ColorP1
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_caja),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Reparto ${reparto.formatoID()}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "S/${reparto.total()}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            DetalleItem(titulo = "Documento", descrip = reparto.cliente.documento)
            DetalleItem(titulo = "Cliente", descrip = reparto.cliente.nombres)
            DetalleItem(titulo = "Teléfono", descrip = reparto.cliente.formatTelefono())
            DetalleItem(titulo = "Distrito", descrip = reparto.cliente.distrito_id.toString())
            DetalleItem(titulo = "Dirección", descrip = reparto.cliente.direc)
            DetalleItem(titulo = "Referencia", descrip = reparto.cliente.referencia)
            DetalleItem(titulo = "Fecha", descrip = reparto.formatFecha())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                FloatingActionButton(
                    onClick = {},
                    containerColor = ColorP2,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = {},
                    containerColor = ColorWsp,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Whatsapp,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = {},
                    containerColor = ColorP1,
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(reparto.items) {
                    Text(text = it.detalle ?: "")
                }
            }
        }
    }
}