package com.jjmf.android.olympuscourier.ui.features.Menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: RepartoRepository,
) : ViewModel() {

    var buscar by mutableStateOf("")
    var activo by mutableStateOf(false)
    var switchTodos by mutableStateOf(true)

    var listRepartos by mutableStateOf<List<Reparto>>(emptyList())
    var listRepartosFiltro by mutableStateOf<List<Reparto>>(emptyList())
    var error by mutableStateOf<String?>(null)

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val res = repository.listarRepartos()) {
                    is EstadosResult.Correcto -> {
                        listRepartos = res.datos ?: emptyList()
                        listRepartosFiltro = listRepartos
                    }
                    is EstadosResult.Error -> error = res.mensajeError
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
}