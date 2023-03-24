package com.jjmf.olympuscourier.ui.features.MovimientosDiarios.screens.RegistrarGasto

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.storage.FirebaseStorage
import com.jjmf.olympuscourier.Core.Compressor
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import com.jjmf.olympuscourier.util.getFecha
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegistrarGastoViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {
    var alertaPrecio by mutableStateOf(false)
    var alertaCamera by mutableStateOf(false)
    var progress by mutableStateOf(false)
    var back by mutableStateOf(false)

    var detalle by mutableStateOf("")
    var costo by mutableStateOf("")

    var imagen by mutableStateOf<Uri?>(null)

    fun insert(context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            progress = true
            try {
                val imgCompresa = Compressor().compressImage(
                    context = context,
                    uri = imagen!!,
                    namePhoto = imagen?.lastPathSegment ?: System.currentTimeMillis().toString()
                )
                val folder = FirebaseStorage.getInstance().reference
                    .child("FotosGastos")
                    .child("${getFecha("ddMMyyyy")}_${System.currentTimeMillis()}.jpg")
                val uriFirebase = folder.putFile(Uri.fromFile(imgCompresa)).await().storage.downloadUrl.await()
                val conformidad = Conformidad(
                    time = Timestamp.now(),
                    codigo = repository.getCorrelativo(),
                    foto = uriFirebase.toString(),
                    costo = -costo.toDouble(),
                    gasto = true,
                    detalleGasto = detalle
                )
                repository.insert(conformidad)
                progress = false
                back = true
            }catch (e:Exception){
                progress = false
            }
        }
    }

    fun getCorrelativo(): Flow<Int> = flow{
            emit(repository.getCorrelativo())
    }
}