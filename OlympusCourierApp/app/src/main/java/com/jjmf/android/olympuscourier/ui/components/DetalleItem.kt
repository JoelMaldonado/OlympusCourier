package com.jjmf.android.olympuscourier.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.olympuscourier.ui.theme.ColorBox
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.ui.theme.ColorT1
import com.jjmf.android.olympuscourier.ui.theme.ColorT2

@Composable
fun DetalleItem(titulo: String, descrip: String, color: Color = ColorT) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = titulo,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium,
            color = color,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier
                .weight(3f)
                .clip(RoundedCornerShape(10.dp))
                .background(ColorBox)
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = descrip, color = ColorT1, fontSize = 14.sp)
        }
    }
}