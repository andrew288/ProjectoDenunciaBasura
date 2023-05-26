package com.example.projectodenunciabasura.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projectodenunciabasura.data.AppDatabase
import com.example.projectodenunciabasura.data.model.Denuncia
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DenunciaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DenunciaRepository
    private val getAllDenuncias: LiveData<List<Denuncia>>

    init {
        val denunciaDao = AppDatabase.getInstance(application).denunciaDao()
        repository = DenunciaRepository(denunciaDao)
        getAllDenuncias = repository.getAllDenuncia
    }

    suspend fun addDenuncia(denuncia: Denuncia): Long {
        return repository.insertDenuncia(denuncia)
    }

    suspend fun getDenunciasByUser(idUser: Int): LiveData<List<DenunciaWithDenunciaImagen>> {
        return repository.getUserWithDenuncias(idUser)
    }

    suspend fun getDenunciasWithImagenByDenuncia(idDenuncia: Long): LiveData<List<DenunciaWithDenunciaImagen>>{
        return repository.getDenunciaImageWithDenuncia(idDenuncia)
    }

}