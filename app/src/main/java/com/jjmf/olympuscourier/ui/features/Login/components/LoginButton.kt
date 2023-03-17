package com.jjmf.olympuscourier.ui.features.Login.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoginButton(
    text: String,
    click: () -> Unit,
) {
    Button(
        onClick = click,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(
            topStart = 20.dp,
            bottomEnd = 20.dp,
            bottomStart = 20.dp
        )
    ) {
        Text(text = text)
    }
}