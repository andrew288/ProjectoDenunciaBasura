package com.example.projectodenunciabasura.Navigation

sealed class Routes(
    val route: String
) {
    object ScreenRegisterAccount : Routes("RegisterAccount")
    object ScreenLoginAccount : Routes("LoginAccount")
    object ScreenHomeAccount: Routes("HomeAccount")
    object ScreenRegisterDenuncia: Routes("RegisterDenuncia")
    object ScreenDetalleDenuncia: Routes("DetalleDenuncia")

}