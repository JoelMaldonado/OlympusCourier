package com.jjmf.olympuscourier.ui.features.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.ColorP1


@Composable
fun LoginScreen(
    toMenu: () -> Unit,
) {
    val user = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }

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
                .fillMaxSize()
                .padding(top = 350.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color.White)
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp),
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
            Text(text = "Usuario", color = ColorP1, fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = user.value,
                onValueChange = { user.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Ingrese su DNI")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = ColorP1,
                    cursorColor = ColorP1,
                    placeholderColor = Color.Gray.copy(0.5f)
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Text(text = "Contraseña", color = ColorP1, fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = pass.value,
                onValueChange = { pass.value = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Ingrese su contraseña")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = ColorP1,
                    cursorColor = ColorP1,
                    placeholderColor = Color.Gray.copy(0.5f)
                ),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = toMenu,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                Text(text = "Login", fontWeight = FontWeight.SemiBold)
            }
        }
    }

}