package com.jjmf.android.olympuscourier

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.storage.FirebaseStorage
import com.jjmf.android.olympuscourier.ui.navigation.NavegacionPrincipal
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.OlympusCourierAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusCourierAppTheme {
                //NavegacionPrincipal()
                //CamaraScreen()

                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(400.dp).padding(15.dp)
                )
            }
        }
    }
}

@Composable
fun CamaraScreen() {
    val foto = remember { mutableStateOf<Bitmap?>(null) }
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = {
            foto.value = it
        }
    )

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                takePicture.launch(null)
            }
        ) {
            Text("Abrir aplicación de cámara")
        }

        Button(onClick = {
            val baos = ByteArrayOutputStream()
            foto.value!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            FirebaseStorage.getInstance().reference.child("Test").child("prueba").putBytes(data)
                .addOnSuccessListener {
                    Log.d("tagito", "Se subio correctamtene")
                }.addOnFailureListener{
                    Log.d("tagito", it.message.toString())
                }
        }) {
            Text(text = "Enviar")
        }

        foto.value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto tomada",
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}