package com.jjmf.android.olympuscourier.ui.features.DarConformidad

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.ui.components.CajaTexto
import com.jjmf.android.olympuscourier.ui.components.CardReparto
import com.jjmf.android.olympuscourier.ui.features.DetalleReparto.IconText
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorP3
import com.jjmf.android.olympuscourier.ui.theme.ColorS1
import com.jjmf.android.olympuscourier.ui.theme.ColorT1


@Composable
fun DarConformidadScreen(
    idReparto: Int,
    back: () -> Unit,
    viewModel: DarConformidadViewModel = hiltViewModel(),
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
            Text(text = "Conformidad de Entrega", color = ColorP1, fontWeight = FontWeight.SemiBold)
        }

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(8.dp))
                .background(ColorP1)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_caja),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = "Reparto N°${reparto.formatoID()}",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        CardConformidad(reparto = reparto)

        CardConformidad2(reparto = reparto, viewModel = viewModel)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorP2,
                contentColor = Color.White
            )
        ) {
            Text(text = "Confirmar Entrega")
        }
    }

}

@Composable
fun CardConformidad2(reparto: Reparto, viewModel: DarConformidadViewModel) {
    val bool = remember { mutableStateOf(true) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Datos de Conformidad",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorP1
                )
                IconButton(onClick = { bool.value = !bool.value }) {
                    val ic =
                        if (bool.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    Icon(imageVector = ic, contentDescription = null)
                }
            }

            AnimatedVisibility(visible = bool.value) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    CajaTexto(
                        valor = viewModel.clave,
                        newValor = { viewModel.clave = it },
                        titulo = "Clave",
                        label = "Ingrese su clave"
                    )
                    Button(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Adjuntar foto", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CardConformidad(reparto: Reparto) {
    val bool = remember { mutableStateOf(true) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Datos del Cliente", fontWeight = FontWeight.SemiBold, color = ColorP1)
                IconButton(onClick = { bool.value = !bool.value }) {
                    val ic =
                        if (bool.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    Icon(imageVector = ic, contentDescription = null)
                }
            }

            AnimatedVisibility(visible = bool.value) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Documento:",
                            modifier = Modifier.weight(1f),
                            color = ColorP3,
                            fontSize = 14.sp
                        )
                        Text(
                            text = reparto.cliente.documento,
                            modifier = Modifier.weight(3f),
                            color = ColorT1,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Nombres:",
                            modifier = Modifier.weight(1f),
                            color = ColorP3,
                            fontSize = 14.sp
                        )
                        Text(
                            text = reparto.cliente.nombres,
                            modifier = Modifier.weight(3f),
                            color = ColorT1,
                            fontSize = 14.sp
                        )
                    }


                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        color = ColorT1.copy(0.5f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tipo Paquete",
                            modifier = Modifier.weight(1f),
                            color = ColorT1,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Cantidad",
                            modifier = Modifier.weight(1f),
                            color = ColorT1,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Precio",
                            modifier = Modifier.weight(1f),
                            color = ColorT1,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = ColorT1.copy(0.5f)
                    )
                    reparto.items.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                IconText(
                                    icon = R.drawable.ic_caja_2,
                                    text = it.id_tipo_paquete.toString()
                                )
                            }
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                IconText(icon = R.drawable.ic_cantidad, text = it.cant.toString())
                            }
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                IconText(icon = R.drawable.ic_precio, text = "S/${it.precio}")
                            }
                        }

                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = ColorT1.copy(0.5f)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(2f))
                        Text(
                            text = "Total: S/${reparto.total}",
                            modifier = Modifier.weight(1f),
                            fontSize = 14.sp,
                            color = ColorT1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}