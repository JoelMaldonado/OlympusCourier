package com.jjmf.olympuscourier.ui.features.Usuarios.ListarUsuarios.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.Data.Model.Usuario
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.util.toMinusculas


@Composable
fun CardUsuario(persona: Usuario, toDetalleUsuario: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${persona.nombres.toMinusculas()} ${persona.apePaterno.toMinusculas()}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                val tipo = if (persona.rol == "A") "Administrador" else "Usuario"
                Text(text = tipo, fontSize = 14.sp)
            }
            IconButton(
                onClick = {
                    toDetalleUsuario()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_editar),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = ColorP1
                )
            }
        }
    }
}