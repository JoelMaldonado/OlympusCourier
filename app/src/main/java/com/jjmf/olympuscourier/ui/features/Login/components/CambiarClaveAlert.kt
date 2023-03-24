package com.jjmf.olympuscourier.ui.features.Login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.features.Login.LoginViewModel
import com.jjmf.olympuscourier.ui.theme.ColorP1


@Composable
fun CambiarClaveAlert(usuario: Usuario, loginViewModel: LoginViewModel = hiltViewModel()) {
    val clave = remember { mutableStateOf("") }
    val isVisible = remember { mutableStateOf(false) }
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cambiar_clave),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Es hora de cambiar tu clave",
                color = ColorP1,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = clave.value,
                onValueChange = { clave.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Ingresa tu nueva clave")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = if (isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isVisible.value = !isVisible.value }) {
                        val icon =
                            if (isVisible.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        Icon(imageVector = icon, contentDescription = null, tint = ColorP1)
                    }
                },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorP1,
                    cursorColor = ColorP1
                )
            )
            Button(
                onClick = {
                    loginViewModel.setNewClave(usuario.copy(clave = clave.value))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                ),
                enabled = clave.value.length >= 4 && !loginViewModel.loaderAlert
            ) {
                if (loginViewModel.loaderAlert) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Text(text = "Guardar")
                }
            }

        }
    }
}