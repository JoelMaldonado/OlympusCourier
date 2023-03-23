package com.jjmf.olympuscourier.ui.features.Usuarios.DetalleUsuario

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleUsuarioViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {
    var progres by mutableStateOf(false)
    var mensaje by mutableStateOf<String?>(null)
    var back by mutableStateOf(false)
    fun delete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            progres = true
            try {
                repository.delete(id)
                back = true
                mensaje = "Eliminado correctamente"
            } catch (e: Exception) {
                mensaje = "Error al eliminar usuario"
            }
            progres = false
        }
    }

}