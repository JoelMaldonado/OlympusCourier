package com.jjmf.android.olympuscourier.ui.features.DarConformidad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DarConformidadViewModel @Inject constructor(
    private val repository: RepartoRepository
) : ViewModel() {

    var error by mutableStateOf<String?>(null)
    var back by mutableStateOf(false)

    var reparto by mutableStateOf<Reparto?>(null)

    var clave by mutableStateOf("")

    fun getReparto(idReparto: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(val res = repository.get(idReparto)){
                    is EstadosResult.Correcto -> reparto = res.datos
                    is EstadosResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

}