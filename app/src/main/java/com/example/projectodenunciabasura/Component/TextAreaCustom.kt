package com.example.projectodenunciabasura.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextAreaCustom(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        containerColor = Color.White,
        focusedIndicatorColor = colorResource(id = R.color.green_light)
    )

    TextField(
        value = value,
        colors = customTextFieldColors,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .padding(start = 20.dp, bottom = 20.dp, end = 20.dp),
        textStyle = TextStyle.Default.copy(textAlign = TextAlign.Start),
        maxLines = Int.MAX_VALUE,
        singleLine = false,
        shape = MaterialTheme.shapes.small,
    )
}