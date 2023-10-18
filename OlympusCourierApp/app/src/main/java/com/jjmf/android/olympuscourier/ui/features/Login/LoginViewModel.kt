package com.jjmf.android.olympuscourier.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    var loaderAlert by mutableStateOf(false)
    var documento by mutableStateOf("")
    var clave by mutableStateOf("")
    var check by mutableStateOf(false)
    var loader by mutableStateOf(false)

}