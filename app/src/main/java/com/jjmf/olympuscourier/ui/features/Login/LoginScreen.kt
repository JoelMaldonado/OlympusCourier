package com.jjmf.olympuscourier.ui.features.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.R


@Composable
fun LoginScreen() {
    val user = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        FondoLogin(
            modifier = Modifier.height(400.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_logo_large_slogan),
                contentDescription = null,
                modifier = Modifier.padding(top = 130.dp).padding(horizontal = 50.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 250.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 100.dp))
                .background(Color.White)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Text(
                text = "Login",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            CajaTexto(
                titulo = "Documento",
                label = "12345678",
                valor = user.value,
                newValor = { user.value = it },
                icon = Icons.Default.Person
            )
            CajaTexto(
                titulo = "Contraseña",
                label = "* * * * * * * * *",
                valor = pass.value,
                newValor = { pass.value = it },
                icon = Icons.Default.Lock
            )

            LoginButton(
                text = "Login",
                click = {
                    //TODO CLICK
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(text = "¿No tienes cuenta? ")
                Text(
                    text = "Registrate",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        //TODO CLICK
                    }
                )
            }
        }
    }
}
@Composable
fun LoginButton(
    text:String,
    click:()->Unit
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
        Text(text =text)
    }
}
@Composable
fun FondoLogin(
    modifier: Modifier,
    composable: @Composable ()->Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        composable()
    }
}

@Composable
fun CajaTexto(
    titulo: String,
    label: String,
    valor: String,
    newValor: (String) -> Unit,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
                Text(
                    text = titulo,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
            Divider()
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                if (valor.isEmpty()) {
                    Text(text = label, color = Color.Gray.copy(0.5f), fontSize = 18.sp)
                }
                BasicTextField(
                    value = valor,
                    onValueChange = newValor,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}