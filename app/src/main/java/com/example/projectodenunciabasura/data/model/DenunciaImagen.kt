package com.example.projectodenunciabasura.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "denuncia_imagen")
class DenunciaImagen(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idDenuncia: Int,
    val image: ByteArray
)