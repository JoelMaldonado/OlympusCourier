package com.jjmf.android.olympuscourier.ui.features.DetalleReparto

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.jjmf.android.olympuscourier.domain.model.ItemReparto
import com.jjmf.android.olympuscourier.ui.components.DetalleItem
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorP3
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.ui.theme.ColorT1
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
                        text = "Reparto #${reparto.formatoID()}",
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
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        try {
                            val mapIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(reparto.cliente.urlMaps))
                            mapIntent.setPackage("com.google.android.apps.maps")
                            context.startActivity(mapIntent)
                        } catch (e: Exception) {

                        }
                    },
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
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://wa.me/${reparto.cliente.telefono}")
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Manejar excepciones según sea necesario
                        }
                    },
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
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_DIAL, Uri.parse("tel:${reparto.cliente.telefono}"))
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    },
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

            reparto.items.forEachIndexed {i, itemReparto ->
                CardItemReparto(
                    index = i,
                    itemReparto = itemReparto
                )
            }
        }
    }
}

@Composable
fun CardItemReparto(
    index:Int,
    itemReparto: ItemReparto,
) {
    val bool = remember { mutableStateOf(index==0) }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Item ${index+1}",
                    color = ColorP1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                IconButton(onClick = { bool.value = !bool.value }) {
                    val ic =
                        if (bool.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    Icon(imageVector = ic, contentDescription = null, tint = ColorP1)
                }
            }
            AnimatedVisibility(visible = bool.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "N° Guia:",
                            modifier = Modifier.weight(1f),
                            color = ColorP3
                        )
                        Text(
                            text = itemReparto.num_guia.ifEmpty { "Sin Guia" },
                            modifier = Modifier.weight(3f),
                            color = ColorT1
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Detalle:",
                            modifier = Modifier.weight(1f),
                            color = ColorP3
                        )
                        Text(
                            text = itemReparto.detalle.ifEmpty { "Sin Detalle" },
                            modifier = Modifier.weight(3f),
                            color = ColorT1
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        color = ColorT1.copy(0.3f)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconText(icon = R.drawable.ic_caja_2, text = itemReparto.id_tipo_paquete.toString())
                        IconText(icon = R.drawable.ic_cantidad, text = itemReparto.cant.toString())
                        IconText(icon = R.drawable.ic_precio, text = "S/${itemReparto.precio}")
                    }

                }
            }

        }

    }
}

@Composable
fun IconText(
    @DrawableRes icon: Int,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(15.dp),
            tint = ColorT1
        )
        Text(text = text, fontSize = 14.sp, color = ColorT1)
    }
}
