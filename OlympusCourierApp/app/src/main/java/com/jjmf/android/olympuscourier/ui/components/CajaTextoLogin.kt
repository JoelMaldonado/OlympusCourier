package com.jjmf.android.olympuscourier.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorT
import com.jjmf.android.olympuscourier.ui.theme.ColorTextoLabel

@Composable
fun CajaTextoLogin(
    valor: String,
    newValor: (String) -> Unit,
    ic: ImageVector,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(1.dp, ColorP1, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(ColorP1),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ic,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 15.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (valor.isEmpty()) {
                Text(text = label, color = ColorTextoLabel)
            }
            BasicTextField(
                value = valor,
                onValueChange = newValor,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation
            )
        }

    }

}