package com.example.projectodenunciabasura.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "denuncia")
data class Denuncia(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idUser: Int,
    val referencia: String,
    val descripcion: String,
    val latitud: String,
    val longitud: String,
)