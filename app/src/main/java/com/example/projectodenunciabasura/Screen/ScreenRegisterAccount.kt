package com.example.projectodenunciabasura.Screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.projectodenunciabasura.data.Repository
import com.example.projectodenunciabasura.data.model.User
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ScreenRegisterAccount(navController: NavController, repository: Repository) {
    // scope curutine
    val scope = rememberCoroutineScope()

    // declaracion de estados
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassWord = remember { mutableStateOf("") }
    var numberPhone = remember { mutableStateOf("") }

    // interfaz de usuario
    Column(
        modifier = Modifier
            .fillMaxSize()
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
            textFieldValue = confirmPassWord,
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
            // validamos los campos
            if (validateFormRegister(
                    firstName.value,
                    lastName.value,
                    username.value,
                    password.value,
                    confirmPassWord.value,
                    numberPhone.value
                )
            ) {
                // registra al usuario y envia a vista LOGIN SCREEN
                scope.launch {
                    val userNew = User(
                        nombre = firstName.value,
                        apellido = lastName.value,
                        username = username.value,
                        password = password.value,
                        telefono =  numberPhone.value)

                    repository.insertUser(userNew)
                }
                // Enviar a vista LOGIN SCREEN
                navController.navigate(Routes.ScreenLoginAccount.route)
            } else {
                // mostramos errores
                Log.d("APP", "ERRORES")
            }

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

fun validateFormRegister(
    nombre: String,
    apellido: String,
    username: String,
    password: String,
    confirmPassword: String,
    telefono: String
): Boolean {
    if (nombre.isNotEmpty() &&
        apellido.isNotEmpty() &&
        username.isNotEmpty() &&
        password.isNotEmpty() &&
        confirmPassword.isNotEmpty() &&
        password == confirmPassword &&
        telefono.length == 9
    ) {
        return true
    }
    return false
}