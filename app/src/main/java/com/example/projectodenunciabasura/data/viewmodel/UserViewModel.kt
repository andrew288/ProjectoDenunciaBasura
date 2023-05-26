package com.example.projectodenunciabasura.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.projectodenunciabasura.data.dao.UserDao
import com.example.projectodenunciabasura.data.model.User

class UserViewModel(private val userDao: UserDao): ViewModel() {
}