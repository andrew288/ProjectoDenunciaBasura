package com.example.projectodenunciabasura.Component
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@Composable
fun ButtonCustom(
    modifier: Modifier = Modifier,
    text: String,
    colorText: Color = Color.Black,
    onClick: () -> Unit,
){
    val customButtonColor = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.green_dark)
    )
    Button(
        onClick = { onClick },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp),
        colors =customButtonColor
    ) {
        Text(text = text, color = colorText)
    }
}