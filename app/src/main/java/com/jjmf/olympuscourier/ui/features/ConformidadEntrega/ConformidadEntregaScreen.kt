package com.jjmf.olympuscourier.ui.features.ConformidadEntrega

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.CajaTexto
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@Composable
fun ConformidadEntregaScreen(
    back:()->Unit,
    viewModel: ConformidadEntregaViewModel = hiltViewModel()
) {

    if (viewModel.back){
        LaunchedEffect(key1 = true){
            back()
            viewModel.back = false
        }
    }

    val focus = LocalFocusManager.current

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    val fuse = LocationServices.getFusedLocationProviderClient(context)
    val cameraState = rememberCameraPositionState() {
        val latlng = LatLng(-12.066178, -77.038662)
        this.position = CameraPosition.fromLatLngZoom(latlng, 8f)
    }
    LaunchedEffect(key1 = true) {
        delay(500)
        coroutine.launch {
            fuse.lastLocation.addOnSuccessListener { locacion ->
                coroutine.launch {
                    val position = LatLng(locacion.latitude, locacion.longitude)
                    cameraState.animate(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.fromLatLngZoom(position, 16f)
                        ), 2000
                    )
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Text(text = "Registra los,", fontWeight = FontWeight.SemiBold, color = ColorP1)
            BigText(text = "Datos de Entrega")
        }
        CajaTexto(
            valor = viewModel.documento,
            newValor = {
                viewModel.documento = it
                if (viewModel.documento.length == 8) {
                    viewModel.reniec()
                }
            },
            titulo = "Documento de Identidad (DNI)",
            label = "Ingrese su DNI",
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
            valor = viewModel.nombres,
            newValor = { viewModel.nombres = it },
            titulo = "Nombres y Apellidos",
            label = "Ingrese su nombre y sus apellidos",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
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
            newValor = { viewModel.celular = it },
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Adjuntar Evidencia", fontWeight = FontWeight.SemiBold, color = ColorP1)
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = null,
                    tint = ColorP1
                )
            }
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp)),
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            properties = MapProperties(isMyLocationEnabled = true),
            cameraPositionState = cameraState
        )
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
                          viewModel.insert()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Guardar")
            }
        }
    }
}