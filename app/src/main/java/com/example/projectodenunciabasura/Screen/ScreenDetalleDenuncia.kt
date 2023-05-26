package com.example.projectodenunciabasura.Screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.BadgeCustom
import com.example.projectodenunciabasura.Component.ImageCarouselRow
import com.example.projectodenunciabasura.Component.TextAreaCustom
import com.example.projectodenunciabasura.Component.TextFieldCustom
import com.example.projectodenunciabasura.Component.TextFieldSimpleCustom
import com.example.projectodenunciabasura.R
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen
import com.example.projectodenunciabasura.data.viewmodel.DenunciaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ScreenDetalleDenuncia(navController: NavController, index: Long) {

    val denunciaModelView: DenunciaViewModel =
        ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(DenunciaViewModel::class.java)

    val denunciaWhitImagen = remember {
        mutableStateOf<List<DenunciaWithDenunciaImagen>>(emptyList())
    }

    // consulta a la base de datos
    var descripcionDenuncia = remember { mutableStateOf("") }
    var referenciaLugar = remember { mutableStateOf("") }
    var imagen1 = remember { mutableStateOf<ImageBitmap?>(null) }
    var imagen2 = remember { mutableStateOf<ImageBitmap?>(null) }
    var fechaDenuncia = remember { mutableStateOf("") }

    LaunchedEffect(Unit){
        val denuncia = withContext(Dispatchers.IO){
            denunciaModelView.getDenunciasWithImagenByDenuncia(index)
        }
        denuncia.observeForever{
                denunciaList ->
            denunciaWhitImagen.value = denunciaList
        }
    }

    val images = listOf(imagen1.value, imagen2.value)

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
                ImageCarouselRow(images = images as List<ImageBitmap>)
            }
        }

    }
}
fun convertByteArrayToImageBitmap(image: ByteArray): ImageBitmap{
    val bitmap = BitmapFactory.decodeByteArray(image,0,image.size)
    val imageBitmap = bitmap.asImageBitmap()
    return imageBitmap
}
