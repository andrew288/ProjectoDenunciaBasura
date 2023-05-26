package com.example.projectodenunciabasura.data.viewmodel

import androidx.lifecycle.LiveData
import com.example.projectodenunciabasura.data.dao.DenunciaDao
import com.example.projectodenunciabasura.data.model.Denuncia
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen

class DenunciaRepository(private val denunciaDao: DenunciaDao) {

    val getAllDenuncia: LiveData<List<Denuncia>> = denunciaDao.getAllDenuncias()

    fun getUserWithDenuncias(idUser: Int): LiveData<List<DenunciaWithDenunciaImagen>> {
        return denunciaDao.getDenunciaWithImages(idUser)
    }

    fun getDenunciaImageWithDenuncia(idDenuncia: Long): LiveData<List<DenunciaWithDenunciaImagen>>{
        return denunciaDao.getDenunciaWithImagesByUser(idDenuncia)
    }

    suspend fun insertDenuncia(denuncia: Denuncia): Long{
        return denunciaDao.insertDenuncia(denuncia)
    }

}