package com.example.projectodenunciabasura.data.viewmodel

import androidx.lifecycle.LiveData
import com.example.projectodenunciabasura.data.dao.DenunciaImagenDao
import com.example.projectodenunciabasura.data.model.DenunciaImagen

class DenunciaImagenRepository(private val denunciaImagenDao: DenunciaImagenDao) {

    val getAllDenunciaImagen: LiveData<List<DenunciaImagen>> = denunciaImagenDao.getAllDenunciaImagen()

    suspend fun insertDenunciaImagen(denunciaImagen: DenunciaImagen){
        return denunciaImagenDao.insertImagenDao(denunciaImagen)
    }
}