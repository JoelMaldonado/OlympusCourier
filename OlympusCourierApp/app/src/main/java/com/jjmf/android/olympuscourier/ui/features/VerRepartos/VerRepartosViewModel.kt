package com.jjmf.android.olympuscourier.ui.features.VerRepartos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.android.olympuscourier.data.firebase.RepartoDto
import com.jjmf.android.olympuscourier.data.repository.RepartoRepository
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.domain.usecases.GetAllRepartosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerRepartosViewModel @Inject constructor(
    private val getAllRepartosUseCase: GetAllRepartosUseCase
) : ViewModel() {

    var listRepartos by mutableStateOf<List<Reparto>>(emptyList())

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getAllRepartosUseCase().collect { list ->
                    listRepartos = list.filter { it.estado == "P" }
                }
            } catch (e: Exception) {

            }
        }

    }

}