package com.example.projectodenunciabasura.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projectodenunciabasura.data.AppDatabase
import com.example.projectodenunciabasura.data.model.DenunciaImagen

class DenunciaImagenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DenunciaImagenRepository
    private val getAllDenunciaImagen: LiveData<List<DenunciaImagen>>

    init {
        val denunciaImagenDao = AppDatabase.getInstance(application).denunciaImagenDao()
        repository = DenunciaImagenRepository(denunciaImagenDao)
        getAllDenunciaImagen = repository.getAllDenunciaImagen
    }

    suspend fun addDenunciaImagen(denunciaImagen: DenunciaImagen) {
        return repository.insertDenunciaImagen(denunciaImagen)
    }
}