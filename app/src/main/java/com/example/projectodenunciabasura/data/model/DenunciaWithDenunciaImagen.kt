package com.example.projectodenunciabasura.data.model

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class DenunciaWithDenunciaImagen(
    @Embedded val denuncia: Denuncia,
    @Relation(
        parentColumn = "id",
        entityColumn = "idDenuncia"
    )
    val denunciaImagenes: List<DenunciaImagen>
)
