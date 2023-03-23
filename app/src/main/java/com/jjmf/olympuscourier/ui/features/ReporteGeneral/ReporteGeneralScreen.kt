package com.jjmf.olympuscourier.ui.features.ReporteGeneral

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jjmf.olympuscourier.R

@Composable
fun ReporteGeneralScreen() {
    Column {
        Image(painter = painterResource(id = R.drawable.ejemplo), contentDescription = null, modifier = Modifier.fillMaxWidth())
        Image(painter = painterResource(id = R.drawable.ejemplo2), contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
}