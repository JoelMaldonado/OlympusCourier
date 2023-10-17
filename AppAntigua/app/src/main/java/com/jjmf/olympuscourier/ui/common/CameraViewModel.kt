package com.jjmf.olympuscourier.ui.common

import android.content.Context
import android.net.Uri
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class CameraViewModel @Inject constructor(
) : ViewModel() {

    var imagen by mutableStateOf<Uri?>(null)
    var cameraProvider: Camera? = null

    fun iniciarCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        imageCapture: ImageCapture,
        previewView: PreviewView,
    ) {
        viewModelScope.launch {
            val cameraSelector =
                CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            val preview = Preview.Builder().build()

            val provider = context.getCameraProvider()
            provider.unbindAll()
            cameraProvider = provider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
    }

    private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine {
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                it.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }

}