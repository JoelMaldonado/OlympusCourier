package com.jjmf.olympuscourier.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.ui.theme.ColorP1

@Composable
fun BigText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = ColorP1,
        fontSize = 35.sp,
        modifier = modifier
    )
}