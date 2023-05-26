package com.example.projectodenunciabasura.data

import androidx.lifecycle.LiveData
import com.example.projectodenunciabasura.data.model.User
import com.example.projectodenunciabasura.data.model.UserWithDenuncia

class Repository(private val appDatabase: AppDatabase) {

    // funcion para verificar la existencia de un usuario
    suspend fun getUserByUsernameAndPassword(username: String, password:String): User? {
        return appDatabase.userDao().getUserByUsernameAndPassword(username, password)
    }

    // funcion para traer los datos de un usuario y sus denuncias
    suspend fun getUserWithDenuncias(idUser: Int): List<UserWithDenuncia> {
        return appDatabase.userDao().getUserWithDenuncias(idUser)
    }

    // registrar un usuario
    suspend fun insertUser(user: User){
        return appDatabase.userDao().addUser(user)
    }
}