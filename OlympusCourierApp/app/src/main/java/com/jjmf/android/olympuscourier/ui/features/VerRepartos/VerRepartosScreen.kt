package com.jjmf.android.olympuscourier.ui.features.VerRepartos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.olympuscourier.ui.components.BigText
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.features.VerRepartos.Components.CardConformidad
import com.jjmf.android.olympuscourier.ui.theme.ColorP1

@Composable
fun VerRepartosScreen(
    toDetalle: (String) -> Unit,
    viewModel: VerRepartosViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        ) {
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                        Text(text = "11/10/2023", color = Color.Gray.copy(0.5f))
                    }
                    BigText(
                        text = "Repartos Diarios"
                    )
                }
            }
            items(viewModel.listRepartos) {
                CardConformidad(
                    reparto = it,
                    toDetalle = {
                        toDetalle(it.id)
                    }
                )
            }

        }
    }
}
