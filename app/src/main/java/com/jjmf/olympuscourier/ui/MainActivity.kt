package com.jjmf.olympuscourier.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.olympuscourier.ui.controller.NavegacionPrincipal
import com.jjmf.olympuscourier.ui.theme.OlympusCourierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusCourierTheme {
                NavegacionPrincipal()
            }
        }
    }
}