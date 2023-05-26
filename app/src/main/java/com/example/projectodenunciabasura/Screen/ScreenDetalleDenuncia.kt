package com.example.projectodenunciabasura.Screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.BadgeCustom
import com.example.projectodenunciabasura.Component.ImageCarouselRow
import com.example.projectodenunciabasura.Component.TextAreaCustom
import com.example.projectodenunciabasura.Component.TextFieldCustom
import com.example.projectodenunciabasura.Component.TextFieldSimpleCustom
import com.example.projectodenunciabasura.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDetalleDenuncia(navController: NavController, index: Int) {
    // consulta a la base de datos
    var descripcionDenuncia = remember { mutableStateOf("") }
    var categoriaEspacioPublico = remember { mutableStateOf("") }
    var referenciaLugar = remember { mutableStateOf("") }
    var imagen1 = remember { mutableStateOf<Bitmap?>(null) }
    var imagen2 = remember { mutableStateOf<Bitmap?>(null) }
    var fechaDenuncia = remember { mutableStateOf("") }

    val images = listOf(
        ImageBitmap.imageResource(R.drawable.logo_add_image),
        ImageBitmap.imageResource(R.drawable.logo_add_image),
        ImageBitmap.imageResource(R.drawable.logo_add_image)
    )

    // interfaz de usuario
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // campo de titulo
        Text(
            text = "Detalle denuncia",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(18.dp)
        )
        Column() {
            // estado
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Estado de denuncia:",
                    color = colorResource(id = R.color.green_dark),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp, start = 20.dp, end = 10.dp)
                )
                BadgeCustom(
                    text = "No atendido",
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            }
            // campo de categoria
            Text(
                text = "Categoria",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp, start = 20.dp)
            )
            TextFieldSimpleCustom(
                value = categoriaEspacioPublico.value,
                onValueChange = {
                    categoriaEspacioPublico.value = it
                },
                readOnly = true
            )
            // referencia de lugar
            Text(
                text = "Referencia de lugar",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp, start = 20.dp)
            )
            TextAreaCustom(
                value = referenciaLugar.value,
                onValueChange = { newText -> referenciaLugar.value = newText },
                readOnly = true
            )
            // descripcion
            Text(
                text = "Descripcion",
                color = colorResource(id = R.color.green_dark),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp, start = 20.dp)
            )
            TextAreaCustom(
                value = descripcionDenuncia.value,
                onValueChange = { newText -> descripcionDenuncia.value = newText },
                readOnly = true
            )

            // SECCION DE FOTOS
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Fotos",
                    color = colorResource(id = R.color.green_dark),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                ImageCarouselRow(images = images)
            }
        }

    }
}