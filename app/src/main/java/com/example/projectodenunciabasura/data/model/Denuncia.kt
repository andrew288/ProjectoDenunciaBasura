package com.example.projectodenunciabasura.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "denuncia")
data class Denuncia(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val idUser: Int,
    val referencia: String,
    val descripcion: String,
    val latitud: String = "",
    val longitud: String = "",
    val fechaDenuncia: String = SimpleDateFormat("dd/MM/yyyy_HHmmss", Locale.getDefault()).format(
        Date()
    )
)