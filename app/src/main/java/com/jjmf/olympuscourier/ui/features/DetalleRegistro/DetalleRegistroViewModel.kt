package com.jjmf.olympuscourier.ui.features.DetalleRegistro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.Data.Repository.ConformidadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleRegistroViewModel @Inject constructor(
    private val repository: ConformidadRepository
) : ViewModel() {

    var alertFoto by mutableStateOf(false)
    var loader by mutableStateOf(false)
    var back by mutableStateOf(false)
    var mensaje by mutableStateOf<String?>(null)

    fun update(conformidad: Conformidad, vigente:Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            loader = true
            when(val wrap = repository.update(conformidad, vigente = vigente)){
                is EstadosResult.Correcto -> {
                    mensaje = wrap.datos
                    loader = false
                    back = true
                }
                is EstadosResult.Error -> {
                    mensaje = wrap.mensajeError
                    loader = false
                }
            }
        }
    }

}