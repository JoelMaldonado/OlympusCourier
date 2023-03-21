package com.jjmf.olympuscourier.ui.features.ListadoPaquetes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1

@Composable
fun ListadoPaquetesScreen(
    toMapa:()->Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                Text(text = "18/03/2023", color = Color.Gray.copy(0.5f))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Buscar fecha: ", color = ColorP1, fontWeight = FontWeight.SemiBold)
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorP1,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
                }
            }
            BigText(
                text = "Lista de Paquetes",
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Datos del paquete:", color = ColorP1, fontWeight = FontWeight.SemiBold)
                FloatingActionButton(
                    onClick = {},
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
        items(list) {
            CardPaquete(paquete = it)
        }
        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorS1,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Regresar")
                }
                Button(
                    onClick = toMapa,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorP1,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Confirmar")
                }
            }
        }
    }
}


data class Paquete(
    val codigo: String,
    val documento: String,
    val nombre: String,
    val apellido: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val region: String,
    val provincia: String,
    val distrito: String
)

val list = listOf(
    Paquete(
        codigo = "000001",
        documento = "74455518",
        "Joel",
        apellido = "Maldonado Fernandez",
        direccion = "Prol. Luis Massaro 118",
        latitud = -12.058036,
        longitud = -77.041778,
        region = "Lima",
        provincia = "Lima",
        distrito = "Cercado de Lima"
    ),
    Paquete(
        codigo = "000002",
        documento = "25143675",
        "Adril Eli",
        apellido = "Albino Tasayco",
        direccion = "Calle Toche 246",
        latitud = -12.057868,
        longitud = -77.039375,
        region = "Lima",
        provincia = "Ri",
        distrito = "Rimac"
    ),
    Paquete(
        codigo = "000003",
        documento = "25666175",
        "Juan Carlos",
        apellido = "Maldonado Reynaga",
        direccion = "Prol. Libertad 741",
        latitud = -12.052370,
        longitud = -77.038560,
        region = "Lima",
        provincia = "Bre",
        distrito = "Breña"
    ),
    Paquete(
        codigo = "000004",
        documento = "65412345",
        "Jean Pier",
        apellido = "Jallorana Lopez",
        direccion = "Urb. Bancarios",
        latitud = -12.044899,
        longitud = -77.038989,
        region = "Lima",
        provincia = "Oli",
        distrito = "Olivos"
    ),

    )

@Composable
fun CardPaquete(paquete: Paquete) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, color = ColorP1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AddBox, contentDescription = null, tint = ColorP1)
                Text(
                    text = "Paquete #${paquete.codigo}",
                    color = ColorP1,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(text = paquete.nombre + " " + paquete.apellido, color = ColorP1)
            Text(text = paquete.direccion, color = ColorP1)
            Text(
                text = "Región: ${paquete.region}        Provincia: ${paquete.provincia}",
                color = ColorP1
            )
            Text(text = "Distrito: ${paquete.distrito}", color = ColorP1)
        }
    }
}
