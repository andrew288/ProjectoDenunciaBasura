package com.example.projectodenunciabasura.Component

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Navigation.Routes
import androidx.compose.ui.graphics.asImageBitmap
import com.example.projectodenunciabasura.R
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen

@Composable
fun CardDenunciaItem(navController: NavController, denuncia: DenunciaWithDenunciaImagen) {
    Log.d("LOGCARD", denuncia.denuncia.descripcion)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate("${Routes.ScreenDetalleDenuncia.route}/${denuncia.denuncia.id}")
            },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Estado: ")
                BadgeCustom(
                    text = "No atendido",
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                bitmap = (covertByteArrayToImageBitmap(denuncia.denunciaImagenes.get(0).image)),
                contentDescription = "Image default",
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = denuncia.denuncia.descripcion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun covertByteArrayToImageBitmap(image: ByteArray): ImageBitmap{
    val bitmap = BitmapFactory.decodeByteArray(image,0,image.size)
    val imageBitmap = bitmap.asImageBitmap()
    return imageBitmap
}