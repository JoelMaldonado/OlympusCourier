package com.jjmf.olympuscourier.ui.features.Usuarios.AgregarPersona

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.jjmf.olympuscourier.Core.EstadosResult
import com.jjmf.olympuscourier.Core.Reniec
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.Data.Repository.UsuarioRepository
import com.jjmf.olympuscourier.util.toMinusculas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarPersonaViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    var loader by mutableStateOf(false)
    var progress by mutableStateOf(false)
    var back by mutableStateOf(false)

    var documento by mutableStateOf("")
    var nombres by mutableStateOf("")
    var apellidos by mutableStateOf("")
    var apePaterno by mutableStateOf("")
    var apeMaterno by mutableStateOf("")
    var fecha by mutableStateOf("")
    var celular by mutableStateOf("")
    var mensaje by mutableStateOf<String?>(null)
    var rol by mutableStateOf(false)
    var correo by mutableStateOf("")
    var alertaDuplicado by mutableStateOf(false)

    fun reniec() {
        viewModelScope.launch(Dispatchers.IO) {
            loader = true
            val call = Reniec().getDocumento().get(documento)
            if (call.isSuccessful) {
                loader = false
                val body = call.body()!!
                nombres = body.nombres.toMinusculas()
                apePaterno = body.apellidoPaterno.toMinusculas()
                apeMaterno = body.apellidoMaterno.toMinusculas()
                apellidos = "$apeMaterno $apeMaterno"
            }else{
                loader = false
            }
        }
    }

    fun insert(){
        viewModelScope.launch(Dispatchers.IO){
            progress = true
            val usuario = Usuario(
                documento = documento,
                clave = "1234",
                nombres = nombres,
                apePaterno = apePaterno,
                apeMaterno = apeMaterno,
                fechaNac = fecha,
                celular = celular,
                rol = if (rol) "A" else "U",
                fechaIngreso = Timestamp.now(),
                correo = correo
            )
            when(val wrap = repository.insert(usuario)){
                is EstadosResult.Correcto -> {
                    back = true
                    progress = false
                    mensaje = wrap.datos
                    documento = ""
                    nombres = ""
                    apellidos = ""
                    apePaterno = ""
                    apeMaterno = ""
                    fecha = ""
                    celular = ""
                    rol = false
                    correo = ""
                }
                is EstadosResult.Error -> {
                    progress = false
                    alertaDuplicado = true
                }
            }
        }
    }

}