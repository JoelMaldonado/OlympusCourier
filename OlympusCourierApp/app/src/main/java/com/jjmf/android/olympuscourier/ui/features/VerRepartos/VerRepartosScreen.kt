package com.jjmf.android.olympuscourier.ui.features.VerRepartos

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.ui.components.BigText
import com.jjmf.android.olympuscourier.ui.components.FondoBlanco
import com.jjmf.android.olympuscourier.ui.components.TotalCard
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorR1
import com.jjmf.android.olympuscourier.ui.features.VerRepartos.Components.CardConformidad
import com.jjmf.android.olympuscourier.ui.features.VerRepartos.Components.CardGasto

@Composable
fun VerRepartosScreen() {

    val context = LocalContext.current

    val mapRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it){

            } else {

            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 30.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                    Text(text = "11/10/2023", color = Color.Gray.copy(0.5f))
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorR1,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reducir),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Registrar Gasto",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }
                BigText(
                    text = "Movimientos Diarios"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Conformidades de entrega:",
                        color = ColorP1,
                        fontWeight = FontWeight.SemiBold
                    )
                    FloatingActionButton(
                        onClick = {
                            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                            } else {
                                mapRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }
                        },
                        containerColor = ColorP1,
                        contentColor = Color.White
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
            items(10) {
                if (it == 1) {
                    CardGasto(
                        toDetalle = {}
                    )
                } else {
                    CardConformidad(
                        toDetalle = {}
                    )
                }
            }
        }


        TotalCard(
            icon = R.drawable.ic_dinero,
            text = "Importe total del día",
            monto = "S/20",
            color = ColorP1,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}