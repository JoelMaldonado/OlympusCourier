package com.jjmf.olympuscourier.ui.features.Usuarios.ListarUsuarios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.ui.features.Usuarios.ListarUsuarios.Components.CardUsuario
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorT1

@Composable
fun ListadoUsuariosScreen(
    toDetalleUsuario: (Usuario) -> Unit,
    viewModel: ListadoUsuariosViewModel = hiltViewModel()
) {

    val isVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getList()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            items(viewModel.listado) {
                CardUsuario(
                    persona = it,
                    toDetalleUsuario = {
                        toDetalleUsuario(it)
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${viewModel.listado.size} encontrados",
                color = ColorT1,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 25.dp, start = 20.dp)
            )
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 5.dp,
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isVisible.value = !isVisible.value
                            }
                            .padding(10.dp)
                    ) {
                        Text(
                            text = viewModel.texto,
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Black
                        )
                        val icono =
                            if (isVisible.value) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                        Icon(
                            imageVector = icono,
                            contentDescription = null,
                            tint = ColorP1,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 10.dp)
                        )
                    }
                    AnimatedVisibility(visible = isVisible.value) {
                        Column {
                            Text(
                                text = "Usuario",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (viewModel.texto == "Usuario") ColorP1.copy(0.8f) else Color.White
                                    )
                                    .clickable {
                                        isVisible.value = false
                                        viewModel.texto = "Usuario"
                                        viewModel.filtro("U")
                                    }
                                    .padding(8.dp),
                                textAlign = TextAlign.Center,
                                color = if (viewModel.texto == "Usuario") Color.White else Color.Black
                            )
                            Text(
                                text = "Admin",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (viewModel.texto == "Admin") ColorP1.copy(0.8f) else Color.White
                                    )
                                    .clickable {
                                        isVisible.value = false
                                        viewModel.texto = "Admin"
                                        viewModel.filtro("A")
                                    }
                                    .padding(8.dp),
                                textAlign = TextAlign.Center,
                                color = if (viewModel.texto == "Admin") Color.White else Color.Black
                            )
                            Text(
                                text = "Todos",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (viewModel.texto == "Todos") ColorP1.copy(0.8f) else Color.White
                                    )
                                    .clickable {
                                        isVisible.value = false
                                        viewModel.texto = "Todos"
                                        viewModel.getList()
                                    }
                                    .padding(8.dp),
                                textAlign = TextAlign.Center,
                                color = if (viewModel.texto == "Todos") Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

