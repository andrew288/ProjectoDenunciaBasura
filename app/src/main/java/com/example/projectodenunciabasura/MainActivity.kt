package com.example.projectodenunciabasura

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectodenunciabasura.Navigation.Routes
import com.example.projectodenunciabasura.Screen.ScreenDetalleDenuncia
import com.example.projectodenunciabasura.Screen.ScreenHomeAccount
import com.example.projectodenunciabasura.Screen.ScreenLoginAccount
import com.example.projectodenunciabasura.Screen.ScreenRegisterAccount
import com.example.projectodenunciabasura.Screen.ScreenRegisterDenuncia
import com.example.projectodenunciabasura.data.AppDatabase
import com.example.projectodenunciabasura.data.Repository
import com.example.projectodenunciabasura.ui.theme.ProjectoDenunciaBasuraTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
        } else {
            Log.i("kilo", "Permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectoDenunciaBasuraTheme {
                // contexto de la base de datos
                val context = LocalContext.current
                val repository = Repository(AppDatabase.getInstance(context.applicationContext))
                val navigationController = rememberNavController()
                NavHost(
                    navController = navigationController,
                    startDestination = Routes.ScreenLoginAccount.route
                ) {
                    composable(Routes.ScreenLoginAccount.route) {
                        ScreenLoginAccount(navController = navigationController, repository = repository)
                    }
                    composable(Routes.ScreenRegisterAccount.route) {
                        ScreenRegisterAccount(navController = navigationController, repository = repository)
                    }
                    composable(Routes.ScreenRegisterDenuncia.route) {
                        ScreenRegisterDenuncia(navController = navigationController, repository = repository)
                    }
                    composable(Routes.ScreenHomeAccount.route) {
                        ScreenHomeAccount(navController = navigationController)
                    }
                    composable(
                        "${Routes.ScreenDetalleDenuncia.route}/{index}",
                        arguments = listOf(navArgument("index") {
                            type = NavType.IntType
                        })
                    ) { backStackEntry ->
                        val index = backStackEntry.arguments?.getInt("index") ?: 0
                        ScreenDetalleDenuncia(
                            navController = navigationController,
                            index = index
                        )
                    }
                }
            }
        }

        // PERMISOS DE LA CAMARA
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("Kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
}