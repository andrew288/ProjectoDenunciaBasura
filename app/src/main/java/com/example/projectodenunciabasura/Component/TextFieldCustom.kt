package com.example.projectodenunciabasura.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    textFieldValue: MutableState<String>,
    textLabel: String,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    paddingCustom: Dp = 16.dp,
    readOnly: Boolean = false
) {
    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        containerColor = Color.White,
        focusedIndicatorColor = colorResource(id = R.color.green_light)
    )
        TextField(
            readOnly = readOnly,
            modifier = Modifier
                .padding(paddingCustom)
                .fillMaxWidth()
                .height(56.dp),
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it },
            label = {
                Text(text = textLabel, color = colorResource(id = R.color.green_light) )
            },
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                capitalization = capitalization,
                keyboardType = keyboardType
            ),
            colors = customTextFieldColors,
            visualTransformation = visualTransformation
        )
}