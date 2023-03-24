package com.jjmf.olympuscourier.ui.features.Usuarios.AgregarPersona

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Patterns
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.olympuscourier.R
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.CajaTexto
import com.jjmf.olympuscourier.ui.theme.ColorP1
import com.jjmf.olympuscourier.ui.theme.ColorS1
import com.jjmf.olympuscourier.util.hayInternet
import com.jjmf.olympuscourier.util.show
import java.util.*

@Composable
fun AgregarPersonaScreen(
    back: () -> Unit,
    viewModel: AgregarPersonaViewModel = hiltViewModel()
) {

    val focus = LocalFocusManager.current
    val context = LocalContext.current

    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }
    if (viewModel.alertaDuplicado) {
        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
            titleText = "Usuario Duplicado"
            contentText = "El documento ${viewModel.documento} ya se encuentra registrado"
            confirmButtonBackgroundColor = ColorP1.hashCode()
            setConfirmButton("Confirmar") {
                dismissWithAnimation()
                viewModel.alertaDuplicado = false
            }
            show()
        }
    }
    viewModel.mensaje?.let {
        context.show(it)
        viewModel.mensaje = null
    }
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(context, R.style.temaDatePicker, { _: DatePicker, a: Int, mes: Int, dia: Int ->
        val d = if (dia < 10) "0$dia" else "$dia"
        val m = if ((mes + 1) < 10) "0${mes + 1}" else "${mes + 1}"
        viewModel.fecha = "$d/$m/$a"
    }, year, month, day)

    datePickerDialog.datePicker.maxDate = calendar.timeInMillis

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            Text(text = "Es hora de,", fontWeight = FontWeight.SemiBold, color = ColorP1)
            BigText(text = "Agregar Personas")
        }

        CajaTexto(
            valor = viewModel.documento,
            newValor = {
                if (it.length < 10) {
                    viewModel.documento = it
                }
                if (viewModel.documento.length == 8) {
                    hayInternet(context){
                        viewModel.reniec()
                    }
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
                }else{
                    if (viewModel.documento.length >= 8) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = ColorP1
                        )
                    }
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
            ),
            trailingIcon = {
                if (viewModel.nombres.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
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
            ),
            trailingIcon = {
                if (viewModel.apellidos.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
        )
        CajaTexto(
            valor = viewModel.fecha,
            newValor = {
                viewModel.fecha = it
            },
            titulo = "Fecha de Nacimiento",
            label = "dd/mm/aaaa",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            trailingIcon = {

                IconButton(
                    onClick = {
                        datePickerDialog.show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            },
            readOnly = true
        )
        CajaTexto(
            valor = viewModel.celular,
            newValor = {
                if (it.length < 10) {
                    viewModel.celular = it
                }
            },
            titulo = "Celular",
            label = "999 999 999",
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = {
                    focus.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number,
            trailingIcon = {
                if (viewModel.celular.length == 9) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
        )
        CajaTexto(
            valor = viewModel.correo,
            newValor = {
                viewModel.correo = it
            },
            titulo = "Correo",
            label = "correocoorporativo@ejemplo.com",
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focus.clearFocus()
                }
            ),
            keyboardType = KeyboardType.Email,
            trailingIcon = {
                if (Patterns.EMAIL_ADDRESS.matcher(viewModel.correo).matches()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = "Rol", color = ColorP1, fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, ColorP1), RoundedCornerShape(10.dp))
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !viewModel.rol,
                    onClick = { viewModel.rol = false },
                    colors = RadioButtonDefaults.colors(selectedColor = ColorP1)
                )
                Text(text = "Usuario", color = Color.Gray.copy(0.5f))
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = viewModel.rol,
                    onClick = { viewModel.rol = true },
                    colors = RadioButtonDefaults.colors(selectedColor = ColorP1)
                )
                Text(text = "Administrador", color = Color.Gray.copy(0.5f))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = back,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorS1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Regresar")
            }
            Button(
                onClick = { viewModel.insert() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorP1,
                    contentColor = Color.White,
                    disabledBackgroundColor = ColorP1
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = !viewModel.progress &&
                        viewModel.documento.isNotEmpty() &&
                        viewModel.documento.length >= 8 &&
                        viewModel.nombres.isNotEmpty() &&
                        viewModel.apellidos.isNotEmpty() &&
                        viewModel.fecha.isNotEmpty() &&
                        viewModel.celular.isNotEmpty() &&
                        viewModel.celular.length == 9
            ) {
                if (viewModel.progress) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                } else {
                    Text(text = "Agregar")
                }
            }
        }
    }
}
