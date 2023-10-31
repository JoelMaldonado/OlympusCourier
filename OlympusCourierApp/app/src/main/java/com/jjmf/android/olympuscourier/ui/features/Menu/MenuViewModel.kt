package com.jjmf.android.olympuscourier.ui.features.Menu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var usuario by mutableStateOf<Usuario?>(null)

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                usuario = repository.getList().find { it.id == prefs.getUserId() }
            }catch (e:Exception){

            }
        }
    }
}