package com.example.projectodenunciabasura.Screen

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.projectodenunciabasura.Component.CardDenunciaItem
import com.example.projectodenunciabasura.Model.Denuncia
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.R
import com.example.projectodenunciabasura.data.DataStoreClass
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen
import com.example.projectodenunciabasura.data.viewmodel.DenunciaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHomeAccount(navController: NavController) {
    var denuncias = remember { mutableStateListOf<Denuncia>(
        Denuncia("DECRIPCION 1", "NO ATENDIDO"),
        Denuncia("DECRIPCION 2", "ATENDIDO"),
        Denuncia("DECRIPCION 3", "EN PROCESO")
    ) }
    // data store
    val dataStore = DataStoreClass(LocalContext.current)
    val savedId = dataStore.getId.collectAsState(initial = -1)

    val denunciaModelView: DenunciaViewModel =
        ViewModelProvider(LocalContext.current as ViewModelStoreOwner).get(DenunciaViewModel::class.java)

    val denunciasWithImagenes = remember {
        mutableStateOf<List<DenunciaWithDenunciaImagen>>(emptyList())
    }

    LaunchedEffect(Unit) {
        val denuncias = withContext(Dispatchers.IO) {
            denunciaModelView.getDenunciasByUser(savedId.value!!)
        }
        denuncias.observeForever{
            denunciasList->
            denunciasWithImagenes.value = denunciasList
        }
    }


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
                    denunciaModelView.viewModelScope.launch {
                            items(denunciasWithImagenes.value) { denuncia ->
                            CardDenunciaItem(denuncia = denuncia, navController = navController)
                        }
                    }
                }
            }
        }
    )
}

fun covertByteArrayToImageBitmap(image: ByteArray): ImageBitmap {
    val bitmap = BitmapFactory.decodeByteArray(image,0,image.size)
    val imageBitmap = bitmap.asImageBitmap()
    return imageBitmap
}
