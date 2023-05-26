package com.example.projectodenunciabasura.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSimpleCustom(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    paddingCustom: Dp = 16.dp,
    readOnly: Boolean = false
) {
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        containerColor = Color.White,
        focusedIndicatorColor = colorResource(id = R.color.green_light)
    )

    TextField(
        value = value,
        readOnly = readOnly,
        colors = customTextFieldColors,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = paddingCustom, end = paddingCustom, bottom = paddingCustom)
            .height(56.dp),
        textStyle = TextStyle.Default.copy(textAlign = TextAlign.Start),
        shape = androidx.compose.material.MaterialTheme.shapes.small,
    )
}