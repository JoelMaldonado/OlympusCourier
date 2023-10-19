package com.jjmf.android.olympuscourier.ui.features.DetalleReparto

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.jjmf.android.olympuscourier.ui.components.AlertaInsertarClave
import com.jjmf.android.olympuscourier.ui.components.DetalleItem
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorS1
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.util.show
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetailRepartoScreen(
    idReparto: String,
    back: () -> Unit,
    viewModel: DetailRepartoViewModel = hiltViewModel(),
) {

    val perms = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            if (uri != null) {
                viewModel.foto = uri
            }
        }
    )

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getReparto(idReparto)
        perms.launchMultiplePermissionRequest()
    }

    if (viewModel.reparto == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    val reparto = viewModel.reparto ?: return

    if (viewModel.alertDarConformidad) {
        AlertaInsertarClave(
            clave = reparto.clave,
            close = {
                viewModel.alertDarConformidad = false
            },
            ok = {
                if (perms.allPermissionsGranted) cameraLauncher.launch(uri)
                else context.show("No tienes permisos de la cámara")

            }
        )
    }


    viewModel.mensaje?.let {
        context.show(it)
        viewModel.mensaje = null
    }

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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(
                    text = "CODIGO #",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorT,
                    fontSize = 22.sp
                )
            }
            DetalleItem(titulo = "Documento", descrip = reparto.cliente.documento)
            DetalleItem(titulo = "Nombres", descrip = reparto.cliente.nombres)
            DetalleItem(titulo = "Apellidos", descrip = reparto.cliente.apellidos)
            DetalleItem(titulo = "Celular", descrip = reparto.cliente.celular)
            DetalleItem(titulo = "Distrito", descrip = reparto.distrito)
            DetalleItem(titulo = "Dirección", descrip = reparto.direc)
            DetalleItem(titulo = "Referencia", descrip = reparto.referencia)
            DetalleItem(titulo = "Fecha", descrip = reparto.formatFecha())
            DetalleItem(titulo = "Total", descrip = "S/${reparto.total}")
            DetalleItem(titulo = "Estado", descrip = reparto.estado)
            if (viewModel.foto != null) {
                AsyncImage(
                    model = viewModel.foto,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.alertDarConformidad = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorP2,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Dar Conformidad")
            }
        }
    }
}


private fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}