package com.jjmf.olympuscourier.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.Core.CheckNetwork
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.controller.NavegacionPrincipal
import com.jjmf.olympuscourier.ui.theme.ColorP1
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

        call()
    }

    private fun call() {
        CheckNetwork(application).observe(this){
            if (it==false){
                SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
                    setCustomImage(R.drawable.ic_sin_internet)
                    titleText = "Sin Conexion a internet"
                    contentText = "Asegurate de estar conectado a una red Wi-Fi o Red de datos"
                    setConfirmButton("Confirmar"){
                        dismissWithAnimation()
                    }
                    confirmButtonBackgroundColor = ColorP1.hashCode()
                    show()
                }
            }
        }
    }
}