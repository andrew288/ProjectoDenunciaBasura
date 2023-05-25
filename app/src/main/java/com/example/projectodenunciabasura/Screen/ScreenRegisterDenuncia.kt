package com.example.projectodenunciabasura.Screen

import android.graphics.Bitmap
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.ButtonCaptureImageCustom
import com.example.projectodenunciabasura.Component.DropDownCategoriaEspacioPublico
import com.example.projectodenunciabasura.Component.TextAreaCustom
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R

@Composable
fun ScreenRegisterDenuncia(navController: NavController) {
    var descripcionDenuncia = remember { mutableStateOf("") }
    var fechaDenuncia = remember { mutableStateOf("") }
    var referenciaLugar = remember { mutableStateOf("") }
    var categories = listOf("Categoria 1", "Categoria 2", "Categoria 3", "Categoria 4")
    var selectedCategory = remember { mutableStateOf(categories[0]) }

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
        // Categoria
        DropDownCategoriaEspacioPublico(
            categories = categories,
            selectedCategory = selectedCategory
        ) { category ->
            selectedCategory.value = category
        }
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
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_light))
                ) {
                    Text(text = "Registrar")
                }
            }
        }
    }
}