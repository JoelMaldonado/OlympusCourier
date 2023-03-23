package com.jjmf.olympuscourier.ui.features.Usuarios

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.features.Usuarios.AgregarPersona.AgregarPersonaScreen
import com.jjmf.olympuscourier.ui.features.Usuarios.ListarUsuarios.ListadoUsuariosScreen
import com.jjmf.olympuscourier.ui.theme.ColorP1
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UsuariosScreen(
    toDetalleUsuario:(Usuario)->Unit
) {
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()){
        FondoBlanco()
        Column {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, top = 15.dp),
                backgroundColor = Color.White,
                contentColor = ColorP1
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        coroutine.launch {
                            pagerState.scrollToPage(0)
                        }
                    },
                    selectedContentColor = ColorP1,
                    unselectedContentColor = Color.Gray.copy(0.5f)
                ) {
                    Text(text = "Usuarios activos", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 5.dp))
                }
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        coroutine.launch {
                            pagerState.scrollToPage(1)
                        }
                    },
                    selectedContentColor = ColorP1,
                    unselectedContentColor = Color.Gray.copy(0.5f)
                ) {
                    Text(text = "Agregar Usuario", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 5.dp))
                }
            }
            HorizontalPager(
                count = 2,
                state = pagerState
            ) {
                when (it) {
                    0 -> ListadoUsuariosScreen(
                        toDetalleUsuario = toDetalleUsuario
                    )
                    1 -> AgregarPersonaScreen(
                        back = {
                            coroutine.launch {
                                pagerState.scrollToPage(0)
                            }
                        }
                    )
                }
            }
        }
    }
}