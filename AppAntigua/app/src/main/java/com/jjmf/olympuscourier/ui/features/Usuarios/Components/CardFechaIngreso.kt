package com.jjmf.olympuscourier.ui.features.Usuarios.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.ui.theme.ColorBox
import com.jjmf.olympuscourier.ui.theme.ColorT
import com.jjmf.olympuscourier.ui.theme.ColorT2


@Composable
fun CardFechaIngreso(fecha: String) {
    Card(
        modifier = Modifier.padding(vertical = 5.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Ingreso",
                fontWeight = FontWeight.Medium,
                color = ColorT,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .background(ColorBox)
                    .padding(5.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = fecha,
                    color = ColorT2,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}