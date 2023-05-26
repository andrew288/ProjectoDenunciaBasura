package com.example.projectodenunciabasura.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectodenunciabasura.data.model.DenunciaImagen

@Dao
interface DenunciaImagenDao {
    @Insert
    suspend fun insertImagenDao(denunciaImagen: DenunciaImagen)
}