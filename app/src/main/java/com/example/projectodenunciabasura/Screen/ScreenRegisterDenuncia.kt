package com.example.projectodenunciabasura.Screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.ButtonCaptureImageCustom
import com.example.projectodenunciabasura.Component.DropDownCategoriaEspacioPublico
import com.example.projectodenunciabasura.Component.TextAreaCustom
import com.example.projectodenunciabasura.Location.LocationViewModel
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R
import com.example.projectodenunciabasura.data.DataStoreClass
import com.example.projectodenunciabasura.data.model.Denuncia
import com.example.projectodenunciabasura.data.model.DenunciaImagen
import com.example.projectodenunciabasura.data.viewmodel.DenunciaImagenViewModel
import com.example.projectodenunciabasura.data.viewmodel.DenunciaViewModel
import com.example.projectodenunciabasura.data.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScreenRegisterDenuncia(navController: NavController) {

    // data store
    val dataStore = DataStoreClass(LocalContext.current)
    val savedId = dataStore.getId.collectAsState(initial = -1)
    // view model
    val denunciaViewModel: DenunciaViewModel =
        ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(DenunciaViewModel::class.java)
    val denunciaImagenViewModel: DenunciaImagenViewModel =
        ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(DenunciaImagenViewModel::class.java)

    var descripcionDenuncia = remember { mutableStateOf("") }
    var referenciaLugar = remember { mutableStateOf("") }
    /*var latitudDenuncia = remember { mutableStateOf("") }
    var longitudDenuncia = remember { mutableStateOf("") }*/
    // implementation camera
    var imageBitmap1 = remember { mutableStateOf<Bitmap?>(null) }
    var imageBitmap2 = remember { mutableStateOf<Bitmap?>(null) }

    // interfaz de usuario
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrar denuncia",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(18.dp)
        )
        //Referencia
        Column {
            Text(
                text = "Referencia de lugar",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp, start = 20.dp)
            )
            TextAreaCustom(value = referenciaLugar.value,
                onValueChange = { newText -> referenciaLugar.value = newText })
        }
        //Descripcion
        Column {
            Text(
                text = "Descripcion",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp, start = 20.dp)
            )
            TextAreaCustom(value = descripcionDenuncia.value,
                onValueChange = { newText -> descripcionDenuncia.value = newText })
        }
        // SECCION DE FOTOS
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Fotos",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonCaptureImageCustom(imageBitmap = imageBitmap1)
                ButtonCaptureImageCustom(imageBitmap = imageBitmap2)
            }
            // ACCIONES
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.ScreenHomeAccount.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.gray_light_custom))
                ) {
                    Text(text = "Cancelar")
                }
                Button(
                    onClick = {
                        if (validateFormRegisterDenuncia(
                                referenciaLugar.value,
                                descripcionDenuncia.value
                            )
                        ) {
                            denunciaViewModel.viewModelScope.launch {
                                val denunciaNew = Denuncia(
                                    idUser = savedId.value!!,
                                    referencia = referenciaLugar.value,
                                    descripcion = descripcionDenuncia.value
                                )
                                val lastInsert: Long = denunciaViewModel.addDenuncia(denunciaNew)

                                if (imageBitmap1.value != null) {
                                    val denunciaImagen1New = DenunciaImagen(
                                        idDenuncia = lastInsert.toInt(),
                                        image = bitmapToByteArray(imageBitmap1.value!!)
                                    )
                                    denunciaImagenViewModel.addDenunciaImagen(denunciaImagen1New)
                                }
                                if (imageBitmap2.value != null) {
                                    val denunciaImagen2New = DenunciaImagen(
                                        idDenuncia = lastInsert.toInt(),
                                        image = bitmapToByteArray(imageBitmap2.value!!)
                                    )
                                    denunciaImagenViewModel.addDenunciaImagen(denunciaImagen2New)
                                }

                            }
                            navController.popBackStack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_light))
                ) {
                    Text(text = "Registrar")
                }
            }
        }
    }
}

fun validateFormRegisterDenuncia(referencia: String, descripcion: String): Boolean {
    return referencia.isNotEmpty() && descripcion.isNotEmpty()
}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}