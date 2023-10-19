package com.jjmf.android.olympuscourier.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.android.olympuscourier.R
import com.jjmf.android.olympuscourier.domain.model.Reparto
import com.jjmf.android.olympuscourier.ui.theme.ColorP2
import com.jjmf.android.olympuscourier.ui.theme.ColorR1
import com.jjmf.android.olympuscourier.ui.theme.ColorT1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertaInsertarClave(
    clave: String,
    close: () -> Unit,
    ok: () -> Unit,
) {
    val comp = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = close
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "Dar Conformidad",
                color = ColorP2,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Column {
                OutlinedTextField(
                    value = comp.value,
                    onValueChange = {
                        error.value = false
                        comp.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Clave")
                    },
                    singleLine = true,
                    maxLines = 1,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = ColorP2,
                        cursorColor = ColorP2
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = error.value
                )
                AnimatedVisibility(visible = error.value) {
                    Text(
                        text = "Clave incorrecta",
                        color = ColorR1,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
                }
            Button(
                onClick = {
                    if (clave == comp.value) {
                        close()
                        ok()
                    } else {
                        error.value = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorP2,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Comprobar")
            }

        }
    }
}