package com.jjmf.android.olympuscourier.ui.features.Menu

import android.content.Context
import android.util.Log
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.android.olympuscourier.ui.components.CardConformidad
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP3
import com.jjmf.android.olympuscourier.ui.theme.ColorS1
import com.jjmf.android.olympuscourier.ui.theme.ColorTextoLabel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    toPerfil:()->Unit,
    toDetalle:(Int)->Unit,
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

            SearchBar(
                query = viewModel.buscar,
                onQueryChange = {
                    viewModel.buscar = it
                    viewModel.activo = true
                },
                onSearch = {
                    Log.d("tagito", it)
                },
                active = viewModel.activo,
                onActiveChange = {
                    viewModel.activo = it
                },
                trailingIcon = {
                    if (viewModel.activo) {
                        IconButton(
                            onClick = {
                                viewModel.buscar = ""
                                viewModel.activo = false
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = if (viewModel.activo) 0.dp else 15.dp)
                    .padding(bottom = if (viewModel.activo) 0.dp else 15.dp),
                placeholder = {
                    Text(
                        text = "Ingresa el codigo de reparto",
                        color = Color.White
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                colors = SearchBarDefaults.colors(
                    containerColor = ColorP3,
                    dividerColor = ColorP1
                )
            ) {

            }
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
                    text = "Total: 12",
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
                    checked = viewModel.soloPendientes,
                    onCheckedChange = {
                        viewModel.soloPendientes = it
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
            ){
                items(viewModel.listRepartos){
                    CardConformidad(reparto = it) {
                        toDetalle(it.id)
                    }
                }
            }
        }
    }

    BackHandler {
        alertaCerrarSesion(
            context = context,
            click = {}
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