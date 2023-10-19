package com.jjmf.android.olympuscourier.ui.features.DetalleReparto

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRepartoViewModel @Inject constructor(
    private val repository: RepartoRepository,
) : ViewModel() {

    var alertDarConformidad by mutableStateOf(false)
    var back by mutableStateOf(false)
    var mensaje by mutableStateOf<String?>(null)
    var foto by mutableStateOf<Uri?>(null)

    var reparto by mutableStateOf<Reparto?>(null)


    fun getReparto(idReparto: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                reparto = repository.get(idReparto)
            }catch (e:Exception){

            }
        }
    }
}