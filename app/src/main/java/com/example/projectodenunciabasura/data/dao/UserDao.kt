package com.example.projectodenunciabasura.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.projectodenunciabasura.data.model.User
import com.example.projectodenunciabasura.data.model.UserWithDenuncia

@Dao
interface UserDao {
    // insertar nuevo usuario
    @Insert
    suspend fun addUser(user: User)

    // select de usuario y denuncias
    @Transaction
    @Query("SELECT * FROM user WHERE id= :idUser")
    suspend fun getUserWithDenuncias(idUser: Int): List<UserWithDenuncia>

    // seleccionar todos los usuarios
    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllData(): List<User>

    // seleccionar un usuario por username y password
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?
}