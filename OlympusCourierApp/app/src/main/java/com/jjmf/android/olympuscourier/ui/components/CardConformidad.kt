package com.jjmf.android.olympuscourier.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.ui.theme.ColorP1
import com.jjmf.android.olympuscourier.ui.theme.ColorT1


@Composable
fun CardConformidad(
    reparto: Reparto,
    toDetalle: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.End
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(Color.White),
            border = BorderStroke(2.dp, color = ColorP1)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_caja),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )

                        Text(
                            text = formatoId(reparto.id),
                            color = ColorP1,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        fontSize = 14.sp,
                        text = "${reparto.cliente?.nombres}",
                        color = ColorT1
                    )
                    Text(
                        fontSize = 14.sp,
                        text = reparto.cliente?.direc ?: "Sin Dirección",
                        color = ColorT1
                    )
                }
                IconButton(
                    onClick = {
                        val mapUri = Uri.parse("https://maps.app.goo.gl/v3ck799EarkjQbSj6")
                        val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
                        context.startActivity(mapIntent)
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){

            OutlinedButton(
                onClick = toDetalle,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = ColorP1
                ),
                border = BorderStroke(2.dp, color = ColorP1),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Detalle", color = ColorT1)
            }
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedButton(
                onClick = toDetalle,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = ColorP1
                ),
                border = BorderStroke(2.dp, color = ColorP1),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Inventory,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Entregar", color = ColorT1)
            }
        }
    }
}

fun formatoId(id: Int): String {
    val idStr = id.toString().take(6).padStart(6, '0')
    return "#$idStr"
}