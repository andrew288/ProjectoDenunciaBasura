package com.example.projectodenunciabasura.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectodenunciabasura.data.model.DenunciaImagen

@Dao
interface DenunciaImagenDao {

    @Query("SELECT * FROM denuncia_imagen ORDER BY id ASC")
    fun getAllDenunciaImagen(): LiveData<List<DenunciaImagen>>

    @Insert
    suspend fun insertImagenDao(denunciaImagen: DenunciaImagen)
}