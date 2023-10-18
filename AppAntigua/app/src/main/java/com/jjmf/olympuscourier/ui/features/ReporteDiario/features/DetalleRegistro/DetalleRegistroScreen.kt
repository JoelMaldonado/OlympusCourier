package com.jjmf.olympuscourier.ui.features.ReporteDiario.features.DetalleRegistro

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import coil.compose.AsyncImage
import com.jjmf.olympuscourier.Data.Model.Conformidad
import com.jjmf.olympuscourier.ui.components.BigText
import com.jjmf.olympuscourier.ui.components.FondoBlanco
import com.jjmf.olympuscourier.ui.features.ReporteDiario.features.DetalleRegistro.Components.AlertaInsertarDetalle
import com.jjmf.olympuscourier.ui.features.Usuarios.Components.DetalleItem
import com.jjmf.olympuscourier.ui.theme.*
import com.jjmf.olympuscourier.util.show
import com.jjmf.olympuscourier.util.sinDatos
import com.jjmf.olympuscourier.util.toFecha

@Composable
fun DetalleRegistroScreen(
    conformidad: Conformidad,
    back: () -> Unit,
    viewModel: DetalleRegistroViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    if (viewModel.back) {
        LaunchedEffect(key1 = Unit) {
            back()
            viewModel.back = false
        }
    }

    if (viewModel.alertaEliminar){
        AlertaInsertarDetalle(conformidad)
    }

    if (viewModel.alertFoto) {
        Dialog(onDismissRequest = { viewModel.alertFoto = false }) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                AsyncImage(
                    model = conformidad.foto,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(40.dp),
                    onClick = { viewModel.alertFoto = false },
                    backgroundColor = ColorP1,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        }
    }

    viewModel.mensaje?.let {
        context.show(it)
        viewModel.mensaje = null
    }

    Text(text = conformidad.toString())
    Box(modifier = Modifier.fillMaxSize()) {
        FondoBlanco()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CODIGO #${conformidad.codigo}",
                    fontWeight = FontWeight.SemiBold,
                    color = ColorT,
                    fontSize = 22.sp
                )
                if (conformidad.vigente == false) {
                    BigText(text = " (Eliminado)", color = ColorR1)
                }
            }
            if (conformidad.gasto == true){
                DetalleItem(titulo = "Detalle", descrip = conformidad.detalleGasto.sinDatos())
            }else{
                DetalleItem(titulo = "Documento", descrip = conformidad.documento.sinDatos())
                DetalleItem(titulo = "Cliente", descrip = conformidad.fullName.sinDatos())
                DetalleItem(titulo = "Celular", descrip = conformidad.celular.sinDatos())
                DetalleItem(titulo = "Dirección", descrip = conformidad.direccion.sinDatos())
                DetalleItem(titulo = "Región", descrip = conformidad.region.sinDatos())
            }
            DetalleItem(titulo = "Usuario", descrip = conformidad.usuarioInsert.sinDatos())
            val fecha = conformidad.time.toFecha()
            DetalleItem(titulo = "Fecha", descrip = "${fecha.diaNum}/${fecha.mes}/${fecha.anio}")
            DetalleItem(titulo = "Hora", descrip = "${fecha.hora}:${fecha.min}pm")
            DetalleItem(titulo = "Importe", descrip = "S/${conformidad.costo}")
            if (conformidad.vigente == false) {
                DetalleItem(
                    titulo = "Eliminado por",
                    descrip = "${conformidad.usuarioDelete}",
                    color = ColorR1
                )
                DetalleItem(
                    titulo = "Motivo",
                    descrip = "${conformidad.motivoDelete}",
                    color = ColorR1
                )
            }
            Text(
                text = "Ver Evidencia",
                color = ColorP1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clickable {
                        viewModel.alertFoto = true
                    }
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.weight(1f))

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
                if (conformidad.vigente == true) {
                    Button(
                        onClick = {
                            viewModel.alertaEliminar = true
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorR1,
                            contentColor = Color.White,
                            disabledBackgroundColor = ColorR1
                        ),
                        shape = RoundedCornerShape(10.dp),
                        enabled = !viewModel.loader
                    ) {
                        if (viewModel.loader) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(25.dp)
                            )
                        } else {
                            Text(text = "Eliminar")
                        }
                    }
                } else {
                    Button(
                        onClick = {
                            alertaRecuperar(
                                context = context,
                                click = {
                                    viewModel.update(conformidad.copy(vigente = true))
                                }
                            )
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = ColorP2,
                            contentColor = Color.White,
                            disabledBackgroundColor = ColorP2
                        ),
                        shape = RoundedCornerShape(10.dp),
                        enabled = !viewModel.loader
                    ) {
                        if (viewModel.loader) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(25.dp)
                            )
                        } else {
                            Text(text = "Restaurar")
                        }
                    }
                }
            }
        }
    }
}


fun alertaRecuperar(context: Context, click: () -> Unit) {
    SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE).apply {
        setCustomImage(com.jjmf.olympuscourier.R.drawable.ic_recuperar)
        titleText = "Recuperar Registro"
        contentText = "¿Estas seguro de querer restaurar este registro?"
        confirmButtonBackgroundColor = ColorP2.hashCode()
        cancelButtonBackgroundColor = ColorS1.hashCode()
        setConfirmButton("Confirmar") {
            dismissWithAnimation()
            click()
        }
        setCancelButton("Cancelar") {
            dismissWithAnimation()
        }
        show()
    }
}