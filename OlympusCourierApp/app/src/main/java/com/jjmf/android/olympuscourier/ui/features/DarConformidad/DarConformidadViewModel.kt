package com.jjmf.android.olympuscourier.ui.features.DarConformidad

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class DarConformidadViewModel @Inject constructor(
    private val repository: RepartoRepository,
) : ViewModel() {

    var error by mutableStateOf<String?>(null)
    var back by mutableStateOf(false)
    var loader by mutableStateOf(false)

    var reparto by mutableStateOf<Reparto?>(null)

    var clave by mutableStateOf("")


    var foto by mutableStateOf<Bitmap?>(null)

    fun getReparto(idReparto: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val res = repository.get(idReparto)) {
                    is EstadosResult.Correcto -> reparto = res.datos
                    is EstadosResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    fun confirmarEntrega(idReparto: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                val urlFoto = if (foto == null) "" else {
                    val baos = ByteArrayOutputStream()
                    foto!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()
                    FirebaseStorage.getInstance().reference.child("Fotos_Repartos")
                        .child(idReparto.toString()).putBytes(data)
                        .await().storage.downloadUrl.await().toString()
                }
                val res = repository.darConformidad(
                    idReparto = idReparto,
                    urlFoto = urlFoto
                )
                when(res){
                    is EstadosResult.Correcto -> {
                        error = res.datos
                        back = true
                    }
                    is EstadosResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                loader = false
            }
        }
    }

}