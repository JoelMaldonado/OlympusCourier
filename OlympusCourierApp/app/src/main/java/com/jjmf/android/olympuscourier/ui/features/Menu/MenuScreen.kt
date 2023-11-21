package com.jjmf.android.olympuscourier.ui.features.Menu

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.android.olympuscourier.ui.components.CajaBuscar
import com.jjmf.android.olympuscourier.ui.components.CardReparto
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorS1
import com.jjmf.android.olympuscourier.ui.theme.ColorTextoLabel

@Composable
fun MenuScreen(
    toPerfil: () -> Unit,
    toDetalle: (Int) -> Unit,
    toDarConformidad: (Int) -> Unit,
    logout: () -> Unit,
    viewModel: MenuViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(ColorP1)
        ) {
            AnimatedVisibility(visible = !viewModel.activo) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Lista de Repartos",
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = toPerfil,
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.ManageAccounts,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            CajaBuscar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 15.dp),
                valor = viewModel.buscar,
                newValor = { new ->
                    viewModel.buscar = new
                    if (new.isEmpty()) {
                        viewModel.listRepartosFiltro = viewModel.listRepartos
                    } else {
                        viewModel.listRepartosFiltro = viewModel.listRepartos.filter {
                            it.id == new.toIntOrNull() || it.cliente.nombres.uppercase()
                                .contains(new.uppercase())
                        }
                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Total: ${viewModel.listRepartos.size}",
                    color = ColorP1,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Pendientes",
                    color = ColorP1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Switch(
                    checked = viewModel.switchTodos,
                    onCheckedChange = {bool->
                        viewModel.switchTodos = bool
                        if (!bool){
                            viewModel.listRepartosFiltro = viewModel.listRepartos.filter { it.estado == "P" }
                        }else{
                            viewModel.listRepartosFiltro = viewModel.listRepartos
                        }
                    },
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = ColorTextoLabel,
                        uncheckedTrackColor = Color.White
                    )
                )
                Text(
                    text = "Todos",
                    color = ColorP1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.listRepartosFiltro) {
                    CardReparto(
                        reparto = it,
                        toDetalle = {
                            toDetalle(it.id)
                        },
                        toDarConformidad = {
                            toDarConformidad(it.id)
                        }
                    )
                }
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
        setCustomImage(com.jjmf.android.olympuscourier.R.drawable.ic_logout)
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