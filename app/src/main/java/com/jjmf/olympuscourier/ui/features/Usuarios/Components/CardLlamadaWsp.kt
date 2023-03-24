package com.jjmf.olympuscourier.ui.features.Usuarios.Components

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.util.show


@Composable
fun CardLLamadaWsp(titulo: String, descrip: String) {
    val context = LocalContext.current
    val callRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${descrip}"))
                context.startActivity(intent)
            } else {
                context.show("Debe aceptar los permisos")
            }
        }
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
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
                    text = titulo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Text(text = descrip, fontSize = 14.sp)
            }
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = {
                    val uri =
                        Uri.parse("https://api.whatsapp.com/send?phone=51${descrip}")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                },
                backgroundColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Whatsapp, contentDescription = null)
            }
            FloatingActionButton(
                modifier = Modifier.size(45.dp),
                onClick = {
                    if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        val intent =
                            Intent(Intent.ACTION_CALL, Uri.parse("tel:${descrip}"))
                        context.startActivity(intent)
                    } else {
                        callRequest.launch(Manifest.permission.CALL_PHONE)
                    }
                },
                backgroundColor = ColorP1.copy(),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null
                )
            }
        }
    }
}
