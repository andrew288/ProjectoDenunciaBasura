package com.example.projectodenunciabasura.data.viewmodel

import androidx.lifecycle.LiveData
import com.example.projectodenunciabasura.data.dao.UserDao
import com.example.projectodenunciabasura.data.model.User

class UserRepository(private val userDao: UserDao) {
    val getAllUser: LiveData<List<User>> =  userDao.getAllUser()

    suspend fun getUserByUsernameAndPassword(username: String, password:String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }
}