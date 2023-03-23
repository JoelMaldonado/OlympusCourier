package com.jjmf.olympuscourier.ui.features.RegistrarGasto

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.common.CameraAlert
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.CajaTexto
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import com.jjmf.olympuscourier.util.show

@Composable
fun RegistrarGastoScreen(
    back: () -> Unit,
    viewModel: RegistrarGastoViewModel = hiltViewModel()
) {

    if (viewModel.back) {
        LaunchedEffect(key1 = true) {
            back()
            viewModel.back = false
        }
    }

    val focus = LocalFocusManager.current
    val context = LocalContext.current

    if (viewModel.alertaCamera) {
        CameraAlert(
            dismiss = {
                viewModel.alertaCamera = false
            },
            img = {
                viewModel.alertaCamera = false
                viewModel.imagen = it
            }
        )
    }

    val cameraRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                viewModel.alertaCamera = true
            } else {
                context.show("Debes aceptar los permisos primero")
            }
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column {
                Text(text = "Registra los,", fontWeight = FontWeight.SemiBold, color = ColorR1)
                BigText(text = "Datos del Gasto", color = ColorR1)
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = ColorR1
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_reducir),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Gasto #${viewModel.getCorrelativo().collectAsState(initial = 0).value}",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            CajaTexto(
                valor = viewModel.detalle,
                newValor = { viewModel.detalle = it },
                titulo = "Detalle",
                label = "Ingrese detalles sobre el gasto",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                color = ColorR1
            )
            CajaTexto(
                valor = viewModel.costo,
                newValor = { viewModel.costo = it },
                titulo = "Costo",
                label = "Ingrese tarifa del servicio",
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focus.clearFocus()
                    }
                ),
                keyboardType = KeyboardType.Number,
                leadingIcon = {
                    Text(text = "S/.", fontWeight = FontWeight.SemiBold, color = ColorR1)
                },
                color = ColorR1
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Adjuntar Evidencia  ",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorR1
                )
                OutlinedButton(
                    onClick = {
                        if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            viewModel.alertaCamera = true
                        } else {
                            cameraRequest.launch(Manifest.permission.CAMERA)
                        }
                    },
                    border = BorderStroke(2.dp, color = ColorR1),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = ColorR1
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (viewModel.imagen != null) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorR1
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = back,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorS1,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Regresar")
                }
                Button(
                    onClick = {
                        viewModel.insert(context)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorR1,
                        contentColor = Color.White
                    ),
                    enabled = !viewModel.progress
                ) {
                    if (viewModel.progress) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    } else {
                        Text(text = "Guardar")
                    }
                }
            }
        }
    }
}