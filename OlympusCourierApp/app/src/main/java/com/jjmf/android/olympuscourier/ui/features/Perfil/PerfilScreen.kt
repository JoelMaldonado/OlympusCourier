package com.jjmf.android.olympuscourier.ui.features.Perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.ui.features.Menu.alertaCerrarSesion
import com.jjmf.android.olympuscourier.ui.theme.ColorFondo
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorT1

@Composable
fun PerfilScreen(
    back: () -> Unit,
    logout: () -> Unit,
    viewModel: PerfilViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    if (viewModel.loader && viewModel.usuario == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = ColorP1
            )
        }
    }

    val usuario = viewModel.usuario ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shadow(15.dp, RoundedCornerShape(bottomEnd = 100.dp))
                .clip(RoundedCornerShape(bottomEnd = 100.dp))
                .background(ColorP1)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = back
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "Bienvenido a tu perfil",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            AsyncImage(
                model = R.drawable.persona,
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "${usuario.nombres} ${usuario.ape_paterno}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ColorP1
            )
            Text(text = usuario.rol, fontSize = 14.sp, color = ColorT1)

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    ItemCardPerfil(
                        icon = Icons.Default.ContactPage,
                        title = "DNI:",
                        descrip = usuario.documento
                    )
                    Divider()
                    ItemCardPerfil(
                        icon = Icons.Default.Phone,
                        title = "Celular:",
                        descrip = usuario.formatTelefono()
                    )
                    Divider()
                    ItemCardPerfil(
                        icon = Icons.Default.Mail,
                        title = "Correo:",
                        descrip = usuario.correo
                    )
                    Divider()
                    ItemCardPerfil(
                        icon = Icons.Default.CalendarMonth,
                        title = "Nacimiento:",
                        descrip = usuario.formatFecha()
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = ColorP1
            )

            Button(
                onClick = {
                    alertaCerrarSesion(
                        context = context,
                        click = logout
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = ColorP1
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                Text(text = "Cerrar Sesión")
            }

        }
    }
}

@Composable
fun ItemCardPerfil(
    icon: ImageVector,
    title: String,
    descrip: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = ColorP1)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1.2f),
            color = ColorP1,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(text = descrip, modifier = Modifier.weight(3f), color = ColorT1, fontSize = 14.sp)
    }
}