package com.jjmf.olympuscourier.ui.features.MovimientosDiarios.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.theme.ColorR1
import com.jjmf.olympuscourier.ui.theme.ColorT1


@Composable
fun CardGasto(
    conformidad: Conformidad,
    toDetalle:()->Unit
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
            elevation = 5.dp,
            backgroundColor = Color.White,
            border = BorderStroke(2.dp, color = ColorR1)
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
                            painter = painterResource(id = R.drawable.ic_decrease),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = ColorR1
                        )
                        Text(
                            text = "CODIGO: #${conformidad.codigo}",
                            color = ColorR1,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        fontSize = 14.sp,
                        text = "${conformidad.detalleGasto}",
                        color = ColorT1
                    )
                }
                Text(
                    text = "S/${conformidad.costo}",
                    color = ColorR1,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        OutlinedButton(
            onClick = toDetalle,
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = ColorR1
            ),
            border = BorderStroke(2.dp, color = ColorR1),
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