package com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.ui.theme.ColorP1


@Composable
fun CardConformidad(conformidad: Conformidad) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, color = ColorP1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AddBox, contentDescription = null, tint = ColorP1)
                Text(
                    text = "Paquete #${conformidad.id}",
                    color = ColorP1,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(text = conformidad.fullName.toString(), color = ColorP1)
            Text(text = conformidad.direccion.toString(), color = ColorP1)
        }
    }
}
