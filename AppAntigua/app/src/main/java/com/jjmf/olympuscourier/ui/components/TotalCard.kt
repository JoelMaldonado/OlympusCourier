package com.jjmf.olympuscourier.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TotalCard(
    @DrawableRes icon: Int,
    text: String,
    monto: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = color,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "S/$monto", color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}