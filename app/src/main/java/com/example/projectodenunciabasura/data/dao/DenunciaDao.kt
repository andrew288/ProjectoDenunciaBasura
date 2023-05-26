package com.example.projectodenunciabasura.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.projectodenunciabasura.data.model.Denuncia
import com.example.projectodenunciabasura.data.model.DenunciaWithDenunciaImagen

@Dao
interface DenunciaDao {
    @Query("SELECT * FROM denuncia ORDER BY id ASC")
    fun getAllDenuncias(): LiveData<List<Denuncia>>

    @Transaction
    @Query("SELECT * FROM denuncia WHERE idUser = :idUser ORDER BY id")
    fun getDenunciaWithImages(idUser: Int): LiveData<List<DenunciaWithDenunciaImagen>>

    @Transaction
    @Query("SELECT * FROM denuncia WHERE id = :idDenuncia ORDER BY id")
    fun getDenunciaWithImagesByUser(idDenuncia: Long): LiveData<List<DenunciaWithDenunciaImagen>>

    @Insert
    suspend fun insertDenuncia(denuncia: Denuncia): Long

    @Delete
    suspend fun delete(denuncia: Denuncia)
}