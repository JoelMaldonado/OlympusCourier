package com.jjmf.android.olympuscourier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.android.olympuscourier.ui.navigation.NavegacionPrincipal
import com.jjmf.android.olympuscourier.ui.theme.OlympusCourierAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusCourierAppTheme {
                NavegacionPrincipal()
            }
        }
    }
}