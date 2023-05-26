package com.example.projectodenunciabasura.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectodenunciabasura.data.AppDatabase
import com.example.projectodenunciabasura.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository
    private val getAllUser: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        getAllUser = repository.getAllUser
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }
    suspend fun checkExistUser(username: String, password: String): User? {
        return repository.getUserByUsernameAndPassword(username, password)
    }
}