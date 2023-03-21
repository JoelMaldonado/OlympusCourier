package com.jjmf.olympuscourier.ui.features.AgregarPersona

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1

@Composable
fun AgregarPersonaScreen(
    viewModel: AgregarPersonaViewModel = hiltViewModel()
) {

    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Text(text = "Es hora de,", fontWeight = FontWeight.SemiBold, color = ColorP1)
            BigText(text = "Agregar Personas")
        }

        CajaTexto(
            valor = viewModel.documento,
            newValor = {
                viewModel.documento = it
                if (viewModel.documento.length == 8) {
                    viewModel.reniec()
                }
            },
            titulo = "Documento de Identidad (DNI)",
            label = "Ingrese su DNI",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                if (viewModel.loader) {
                    CircularProgressIndicator(color = ColorP1, modifier = Modifier.size(25.dp))
                }
            }
        )
        CajaTexto(
            valor = viewModel.nombres,
            newValor = { viewModel.nombres = it },
            titulo = "Nombre",
            label = "Ingrese su nombre",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )
        CajaTexto(
            valor = viewModel.apellidos,
            newValor = { viewModel.apellidos = it },
            titulo = "Apellidos",
            label = "Ingrese sus apellidos",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )
        CajaTexto(
            valor = viewModel.direccion,
            newValor = { viewModel.direccion = it },
            titulo = "Dirección",
            label = "Ingrese su dirección",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            )
        )
        CajaTexto(
            valor = viewModel.celular,
            newValor = { viewModel.celular = it },
            titulo = "Celular",
            label = "999 999 999",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number
        )
        CajaTexto(
            valor = viewModel.rol,
            newValor = { viewModel.rol = it },
            titulo = "Rol",
            label = "Seleccion el rol del usuario",
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focus.clearFocus()
                }
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorS1,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Regresar")
            }
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Agregar")
            }
        }
    }
}

@Composable
fun CajaTexto(
    valor: String,
    newValor: (String) -> Unit,
    titulo: String,
    label: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = titulo, color = ColorP1, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = valor,
            onValueChange = newValor,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                unfocusedBorderColor = ColorP1,
                focusedBorderColor = ColorP1,
                cursorColor = ColorP1,
                placeholderColor = Color.Gray.copy(0.5f)
            ),
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(text = label)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon
        )
    }
}
