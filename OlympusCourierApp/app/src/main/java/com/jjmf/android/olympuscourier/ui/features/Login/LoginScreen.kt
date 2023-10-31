package com.jjmf.android.olympuscourier.ui.features.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.ui.components.CajaTexto
import com.jjmf.android.olympuscourier.ui.theme.ColorP1

@Composable
fun LoginScreen(
    toMenu: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val isVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val focus = LocalFocusManager.current

    if(viewModel.toMenu){
        LaunchedEffect(key1 = Unit){
            viewModel.toMenu = false
            toMenu()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color.White)
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Bienvenido",
                color = ColorP1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.ic_logo_large),
                contentDescription = null,
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CajaTexto(
                valor = viewModel.documento,
                newValor = {
                    if (it.length < 10) {
                        viewModel.documento = it
                    }
                },
                titulo = "Usuario",
                label = "Ingrese su documento",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focus.moveFocus(FocusDirection.Down)
                    }
                )
            )
            CajaTexto(
                valor = viewModel.clave,
                newValor = {
                    if (it.length < 9) {
                        viewModel.clave = it
                    }
                },
                titulo = "Contraseña",
                label = "Ingrese su contraseña",
                keyboardType = KeyboardType.Number,
                trailingIcon = {
                    val icon =
                        if (isVisible.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(
                        onClick = {
                            isVisible.value = !isVisible.value
                        }
                    ) {
                        Icon(imageVector = icon, contentDescription = null, tint = ColorP1)
                    }
                },
                visualTransformation = if (isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focus.clearFocus()
                    }
                )
            )
            RecordarUsuario()
            Spacer(modifier = Modifier.height(5.dp))

            if (viewModel.loader){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }else{
                Button(
                    onClick = {
                        viewModel.login()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorP1,
                        contentColor = Color.White,
                        disabledContainerColor = ColorP1
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(15.dp)
                ) {
                    Text(text = "Ingresar", fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}


@Composable
fun RecordarUsuario(viewModel: LoginViewModel = hiltViewModel()) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = viewModel.check,
            onCheckedChange = { viewModel.check = it },
            colors = CheckboxDefaults.colors(checkedColor = ColorP1, checkmarkColor = Color.White)
        )
        Text(text = "Recordar usuario", fontSize = 14.sp)
    }
}