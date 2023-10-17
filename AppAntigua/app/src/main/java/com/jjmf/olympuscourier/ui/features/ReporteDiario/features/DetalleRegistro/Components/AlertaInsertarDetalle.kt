package com.jjmf.olympuscourier.ui.features.ReporteDiario.features.DetalleRegistro.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.features.ReporteDiario.features.DetalleRegistro.DetalleRegistroViewModel
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.ui.theme.ColorT1

@Composable
fun AlertaInsertarDetalle(
    conformidad: Conformidad,
    viewModel: DetalleRegistroViewModel = hiltViewModel()
) {
    val clave = remember { mutableStateOf("") }
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Eliminar Registro",
                color = ColorR1,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "¿Estas seguro de querer eliminar este registro?",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = ColorT1
            )
            OutlinedTextField(
                value = clave.value,
                onValueChange = { clave.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Motivo de eliminación")
                },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorR1,
                    cursorColor = ColorR1
                )
            )
            Button(
                onClick = {
                    viewModel.update(conformidad.copy(vigente = false, motivoDelete = clave.value))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorR1,
                    contentColor = Color.White
                ),
                enabled = clave.value.length >= 4 && !viewModel.loader
            ) {
                if (viewModel.loader) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Text(text = "Eliminar")
                }
            }

        }
    }
}