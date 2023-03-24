package com.jjmf.olympuscourier.ui.features.Usuarios.ListarUsuarios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import com.jjmf.olympuscourier.app.BaseApp.Companion.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ListadoUsuariosViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel(){

    var texto by mutableStateOf("Todos")
    var listado by mutableStateOf<List<Usuario>>(emptyList())

    fun getList(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getListFlow().collect(){list->
                listado = list.filter { it.id != prefs.getUser()?.id && it.admin == false }
            }
        }
    }

    fun filtro(s: String) {
        viewModelScope.launch(Dispatchers.IO){
            listado = repository.getList().filter { it.rol == s }
        }
    }
}