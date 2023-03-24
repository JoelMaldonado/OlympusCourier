package com.jjmf.olympuscourier.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.ColorP1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toLogin: () -> Unit
) {

    val bool = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(500)
        bool.value = true
        delay(1000)
        toLogin()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP1)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo_large_slogan_white),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 200.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.persona),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(500.dp)
        )
    }
}