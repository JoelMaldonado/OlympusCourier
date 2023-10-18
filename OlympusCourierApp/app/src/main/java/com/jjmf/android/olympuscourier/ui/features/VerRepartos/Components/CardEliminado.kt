package com.jjmf.android.olympuscourier.ui.features.VerRepartos.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.ui.theme.ColorS1
import com.jjmf.android.olympuscourier.ui.theme.ColorT1


@Composable
fun CardEliminado(
    toDetalle: () -> Unit
) {
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
            border = BorderStroke(2.dp, color = ColorS1)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = ColorS1
                        )
                        Text(
                            text = "CODIGO: #000002",
                            color = ColorS1,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(text = "Eliminado", fontWeight = FontWeight.SemiBold, color = ColorS1)
                    }
                    Text(
                        fontSize = 14.sp,
                        text = "Eliminado por: Joel",
                        color = ColorT1
                    )
                }
                Text(
                    text = "S/30}",
                    color = ColorS1,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
        OutlinedButton(
            onClick = toDetalle,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = ColorS1
            ),
            border = BorderStroke(2.dp, color = ColorS1),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_detalle),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Ver Detalle", color = ColorT1)
        }
    }
}