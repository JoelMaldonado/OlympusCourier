package com.jjmf.olympuscourier.ui.features.Mapa

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@Composable
fun MapaScreen() {
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


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        properties = MapProperties(isMyLocationEnabled = true),
        cameraPositionState = cameraState
    )
}