package com.jjmf.olympuscourier.ui.common

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.olympuscourier.R.string
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import java.io.File
import java.util.concurrent.Executors


@Composable
fun CameraAlert(
    dismiss: () -> Unit,
    img: (Uri?) -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }

    LaunchedEffect(true) {
        viewModel.iniciarCamera(
            context = context,
            lifecycleOwner = lifecycleOwner,
            imageCapture = imageCapture,
            previewView = previewView
        )
    }

    Dialog(
        onDismissRequest = dismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (viewModel.imagen != null) {
                AsyncImage(
                    model = viewModel.imagen,
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /**Volver a tomar Foto**/
                    Button(
                        onClick = {
                            viewModel.imagen = null
                            viewModel.iniciarCamera(
                                context = context,
                                lifecycleOwner = lifecycleOwner,
                                imageCapture = imageCapture,
                                previewView = previewView
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorS1,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = null
                        )
                    }
                    /**Confirmar**/
                    Button(
                        onClick = {
                            img(viewModel.imagen)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorP1,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            } else {
                AndroidView(
                    factory = {
                        previewView
                    },
                    modifier = Modifier.fillMaxSize()
                )
                Button(
                    onClick = {
                        tomarFoto(
                            nombreFoto = System.currentTimeMillis().toString(),
                            imageCapture = imageCapture,
                            onImagenCapturada = {
                                viewModel.imagen = it
                            },
                            context = context,
                            onError = {}
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorP1,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Default.Camera, contentDescription = null)
                }
            }
        }
    }
}

private fun tomarFoto(
    nombreFoto: String,
    imageCapture: ImageCapture,
    onImagenCapturada: (Uri) -> Unit,
    context: Context,
    onError: (ImageCaptureException) -> Unit,
) {
    val photoFile = File(getOutputDirectory(context), "$nombreFoto.jpg")
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    val cameraExecutor = Executors.newSingleThreadExecutor()

    imageCapture.takePicture(outputOptions,
        cameraExecutor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onImagenCapturada(Uri.fromFile(photoFile))
                cameraExecutor.shutdown()
            }
        })
}

private fun getOutputDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(
            it,
            context.resources.getString(string.app_name)
        ).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}