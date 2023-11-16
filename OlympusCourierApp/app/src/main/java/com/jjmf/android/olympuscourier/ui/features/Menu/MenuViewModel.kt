package com.jjmf.android.olympuscourier.ui.features.Menu

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.core.EstadosResult
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.data.repository.UsuarioRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: RepartoRepository
) : ViewModel() {


    var buscar by mutableStateOf("")
    var activo by mutableStateOf(false)
    var soloPendientes by mutableStateOf(false)

    var listRepartos by mutableStateOf<List<Reparto>>(emptyList())

    fun init() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = repository.listarRepartos()
                when(res){
                    is EstadosResult.Correcto -> {
                        listRepartos = res.datos ?: emptyList()
                        Log.d("tagito", listRepartos.toString())
                    }
                    is EstadosResult.Error -> {
                        Log.d("tagito", res.mensajeError)
                    }
                }
            }catch (e:Exception){

            }
        }
    }
}