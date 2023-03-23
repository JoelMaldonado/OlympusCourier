package com.jjmf.olympuscourier.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jjmf.olympuscourier.ui.theme.ColorP1


@Composable
fun CajaTexto(
    valor: String,
    newValor: (String) -> Unit,
    titulo: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly : Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    color: Color = ColorP1
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = titulo, color = color, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = valor,
            onValueChange = newValor,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = color,
                focusedBorderColor = color,
                cursorColor = color,
                placeholderColor = Color.Gray.copy(0.5f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(text = label)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            readOnly = readOnly,
            singleLine = true,
            maxLines = 1,
            visualTransformation = visualTransformation
        )
    }
}