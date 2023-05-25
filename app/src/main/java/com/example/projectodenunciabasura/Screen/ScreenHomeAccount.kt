package com.example.projectodenunciabasura.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.CardDenunciaItem
import com.example.projectodenunciabasura.Model.Denuncia
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHomeAccount(navController: NavController) {
    var denuncias = remember { mutableStateListOf<Denuncia>(
        Denuncia("DECRIPCION 1", "NO ATENDIDO"),
        Denuncia("DECRIPCION 2", "ATENDIDO"),
        Denuncia("DECRIPCION 3", "EN PROCESO")
    ) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.ScreenRegisterDenuncia.route)
                },
                modifier = Modifier.clip(CircleShape),
                containerColor = colorResource(id = R.color.green_dark),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_basic_add),
                    contentDescription = "Agregar",
                )
            }
        },
        containerColor = Color.White,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Mis denuncias",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.green_dark)
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(denuncias) { denuncia ->
                        CardDenunciaItem(denuncia = denuncia)
                    }
                }
            }
        }
    )
}