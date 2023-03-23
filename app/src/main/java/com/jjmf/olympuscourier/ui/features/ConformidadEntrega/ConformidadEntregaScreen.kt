package com.jjmf.olympuscourier.ui.features.ConformidadEntrega

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import com.google.android.gms.location.LocationServices
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.common.CameraAlert
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.CajaTexto
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import com.jjmf.olympuscourier.util.obtenerDireccion
import com.jjmf.olympuscourier.util.show

@SuppressLint("MissingPermission")
@Composable
fun ConformidadEntregaScreen(
    back: () -> Unit,
    viewModel: ConformidadEntregaViewModel = hiltViewModel()
) {

    if (viewModel.back) {
        LaunchedEffect(key1 = true) {
            back()
            viewModel.back = false
        }
    }

    val focus = LocalFocusManager.current

    val context = LocalContext.current
    val fuse = LocationServices.getFusedLocationProviderClient(context)
    fuse.lastLocation.addOnSuccessListener {
        obtenerDireccion(context, it).apply {
            viewModel.direccion = this.calle
            viewModel.region = this.region
        }
    }

    if (viewModel.alertaCamera) {
        CameraAlert(
            dismiss = {
                viewModel.alertaCamera = false
            },
            img = {
                viewModel.imagen = it
                viewModel.alertaCamera = false
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column {
                Text(text = "Registra los,", fontWeight = FontWeight.SemiBold, color = ColorP1)
                BigText(text = "Datos de Entrega")
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = ColorP1
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_caja),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Conformidad #${
                            viewModel.getCorrelativo().collectAsState(initial = 0).value
                        }",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            CajaTexto(
                valor = viewModel.documento,
                newValor = {
                    if (it.length < 10) viewModel.documento = it
                    if (viewModel.documento.length == 8) {
                        viewModel.reniec()
                    }
                },
                titulo = "Documento de Identidad (DNI)",
                label = "Ingrese documento del cliente",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardType = KeyboardType.Number,
                trailingIcon = {
                    if (viewModel.loader) {
                        CircularProgressIndicator(color = ColorP1, modifier = Modifier.size(25.dp))
                    }
                }
            )
            CajaTexto(
                valor = viewModel.fullName,
                newValor = { viewModel.fullName = it },
                titulo = "Nombres y Apellidos",
                label = "Ingrese nombres y apellidos",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                readOnly = true
            )
            CajaTexto(
                valor = viewModel.direccion,
                newValor = { viewModel.direccion = it },
                titulo = "Dirección",
                label = "Ingrese su dirección",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                )
            )
            CajaTexto(
                valor = viewModel.celular,
                newValor = {
                    if (it.length < 10) viewModel.celular = it
                },
                titulo = "Celular",
                label = "999 999 999",
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardType = KeyboardType.Number
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
                    Text(text = "S/.", fontWeight = FontWeight.SemiBold, color = ColorP1)
                }
            )
            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Adjuntar Evidencia  ",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorP1
                )
                OutlinedButton(
                    onClick = {
                        if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            viewModel.alertaCamera = true
                        } else {
                            cameraRequest.launch(Manifest.permission.CAMERA)
                        }
                    },
                    border = BorderStroke(2.dp, color = ColorP1),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = ColorP1
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
                        tint = ColorP1
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
                        fuse.lastLocation.addOnSuccessListener {
                            viewModel.insert(context = context, location = it)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorP1,
                        contentColor = Color.White,
                        disabledBackgroundColor = ColorP1
                    ),
                    enabled = !viewModel.progress &&
                            (viewModel.documento.isNotEmpty() &&
                                    viewModel.fullName.isNotEmpty() &&
                                    viewModel.direccion.isNotEmpty() &&
                                    viewModel.celular.isNotEmpty() &&
                                    viewModel.costo.isNotEmpty() &&
                                    viewModel.imagen != null)
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