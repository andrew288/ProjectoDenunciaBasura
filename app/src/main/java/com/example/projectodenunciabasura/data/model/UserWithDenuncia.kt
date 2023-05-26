package com.example.projectodenunciabasura.data.model

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class UserWithDenuncia(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "idUser"
    )
    val denuncias: List<Denuncia>
)

