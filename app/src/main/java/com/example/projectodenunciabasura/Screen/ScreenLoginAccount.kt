package com.example.projectodenunciabasura.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.ButtonCustom
import com.example.projectodenunciabasura.Component.TextFieldCustom
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R

@Composable
fun ScreenLoginAccount(navController: NavController) {
    // definicion de variables
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    // interfaz de usuario
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // IMAGE
        Image(
            painter = painterResource(id = R.drawable.logo_2_aplicacion), // Reemplaza R.drawable.logo con el ID de tu imagen de logo
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp) // Ajusta el tamaño de acuerdo a tus necesidades
        )
        Text(
            text = "Iniciar sesion",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(16.dp)
        )
        TextFieldCustom(
            textFieldValue = username,
            textLabel = "Username",
            keyboardType = KeyboardType.Text,
        )
        TextFieldCustom(
            textFieldValue = password,
            textLabel = "Password",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        ButtonCustom(text = "Ingresar", colorText = Color.White) {
            // Codigo para consultar en base de datos la existencia del usuario
        }

        Text(
            text = "¿No tienes una cuenta? Regístrate",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Routes.ScreenRegisterAccount.route) }
        )
    }

}