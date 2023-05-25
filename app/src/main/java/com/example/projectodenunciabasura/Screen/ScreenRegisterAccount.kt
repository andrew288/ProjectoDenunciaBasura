package com.example.projectodenunciabasura.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.ButtonCustom
import com.example.projectodenunciabasura.Component.TextFieldCustom
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R

@Composable
fun ScreenRegisterAccount(navController: NavController){
    // declaracion de estados
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var username = remember{ mutableStateOf("") }
    var password = remember{ mutableStateOf("") }
    var confirmPassWord = remember{ mutableStateOf("") }
    var numberPhone = remember{ mutableStateOf("") }

    // interfaz de usuario
    Column(
        modifier = Modifier.
        fillMaxSize()
            .padding(vertical = 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrarse",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(16.dp)
        )
        TextFieldCustom(
            textFieldValue = firstName,
            textLabel = "Nombres",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = lastName,
            textLabel = "Apellidos",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = username,
            textLabel = "Username",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = password,
            textLabel = "Contraseña",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp,
            visualTransformation = PasswordVisualTransformation()
        )
        TextFieldCustom(
            textFieldValue = password,
            textLabel = "Confirmar contraseña",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp,
            visualTransformation = PasswordVisualTransformation()
        )
        TextFieldCustom(
            textFieldValue = numberPhone,
            textLabel = "Telefono",
            paddingCustom = 10.dp,
            keyboardType = KeyboardType.Number,
        )
        ButtonCustom(text = "Registrarse", colorText = Color.White) {
            // Codigo para registrar un usuario
        }
        Text(
            text = "¿Ya tienes una cuenta? Iniciar sesion",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Routes.ScreenLoginAccount.route) }
        )
    }
}