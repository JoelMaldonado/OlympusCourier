package com.jjmf.android.olympuscourier.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.app.BaseApp.Companion.prefs
import com.jjmf.android.olympuscourier.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    var documento by mutableStateOf(prefs.getDoc() ?: "")
    var clave by mutableStateOf("")
    var check by mutableStateOf(prefs.getRecordar())

    var toMenu by mutableStateOf(false)
    var loader by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)


    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loader = true
                if (loginUseCase(doc = documento, clave = clave)) {
                    if (check) prefs.saveDoc(documento)
                    else prefs.removeDoc()
                    prefs.saveRecordar(check)
                    toMenu = true
                }else{
                    error = "Sin acceso"
                }
            } catch (e: Exception) {
                error = e.message
            }finally {
                loader = false
            }
        }
    }

}