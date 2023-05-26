package com.example.projectodenunciabasura.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectodenunciabasura.data.dao.DenunciaDao
import com.example.projectodenunciabasura.data.dao.DenunciaImagenDao
import com.example.projectodenunciabasura.data.dao.UserDao
import com.example.projectodenunciabasura.data.model.Denuncia
import com.example.projectodenunciabasura.data.model.DenunciaImagen
import com.example.projectodenunciabasura.data.model.User

@Database(
    entities = [User::class, Denuncia::class, DenunciaImagen::class],
    version = 3, exportSchema = true
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun denunciaDao(): DenunciaDao
    abstract fun denunciaImagenDao(): DenunciaImagenDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, "database-denuncia-basura"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}
