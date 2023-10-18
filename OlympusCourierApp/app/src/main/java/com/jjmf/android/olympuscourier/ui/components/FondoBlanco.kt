package com.jjmf.android.olympuscourier.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jjmf.android.olympuscourier.R

@Composable
fun FondoBlanco() {
    Image(
        painter = painterResource(id = R.drawable.fondo_blanco),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}