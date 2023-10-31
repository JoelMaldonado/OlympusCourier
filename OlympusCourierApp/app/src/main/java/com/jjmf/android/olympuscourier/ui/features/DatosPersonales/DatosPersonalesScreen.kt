package com.jjmf.android.olympuscourier.ui.features.DatosPersonales

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.olympuscourier.ui.components.DetalleItem
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorBox
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorR1
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.ui.theme.ColorT2

@Composable
fun DatosPersonalesScreen(
    viewModel: DatosPersonalesViewModel = hiltViewModel(),
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    if (viewModel.user == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    val usuario = viewModel.user ?: return
    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mis Datos laborales",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorP1,
                    fontSize = 22.sp
                )
            }
            DetalleItem(titulo = "Documento", descrip = usuario.doc)
            DetalleItem(titulo = "Nombres", descrip = usuario.nombres)
            DetalleItem(
                titulo = "Apellidos",
                descrip = usuario.apePaterno
            )
            DetalleItem(titulo = "Fecha Nac.", descrip = usuario.formatFecha())
            val rol =
                if (usuario.rol == "A") "Administrador" else if (usuario.rol == "U") "Usuario" else "Sin Rol"
            DetalleItem(titulo = "Tipo Usuario", descrip = rol)
            DetalleItem(titulo = "Celular", descrip = usuario.celular)
            DetalleItem(titulo = "Correo", descrip = usuario.correo)
            Spacer(modifier = Modifier.weight(1f))
            if (viewModel.loader) {
                CircularProgressIndicator(
                    color = ColorR1,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)
                )
            } else {
                Text(
                    text = "Eliminar Cuenta",
                    color = ColorR1,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)
                        .clickable {
                        }
                )
            }
        }
    }
}