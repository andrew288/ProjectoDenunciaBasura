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
    @Query("SELECT * FROM denuncia")
    suspend fun getAllDenuncias(): List<Denuncia>

    @Transaction
    @Query("SELECT * FROM denuncia")
    suspend fun getDenunciaWithImages(): List<DenunciaWithDenunciaImagen>

    @Insert
    suspend fun insert(denuncia: Denuncia)

    @Delete
    suspend fun delete(denuncia: Denuncia)
}