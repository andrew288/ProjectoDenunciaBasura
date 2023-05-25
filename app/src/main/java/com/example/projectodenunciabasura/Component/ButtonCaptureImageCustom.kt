package com.example.projectodenunciabasura.Component

import android.graphics.Bitmap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@Composable
fun ButtonCaptureImageCustom(
    imageBitmap: MutableState<Bitmap?>
) {
    val takePicture =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview(),
            onResult = { bitmap ->
                imageBitmap.value = bitmap
            })

    Button(
        onClick = {
            takePicture.launch()
        },
        modifier = Modifier
            .size(150.dp)
            .clipToBounds()
            .background(
                colorResource(id = R.color.gray_light_custom),
                shape = RectangleShape
            ),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.gray_light_custom))
    ) {
        if (imageBitmap.value != null) {
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = imageBitmap.value!!.asImageBitmap(),
                contentDescription = "Imagen capturada"
            )
        } else {
            Image(
                painter = painterResource(R.drawable.logo_add_image),
                contentDescription = "Agregar foto",
            )
        }
    }
}