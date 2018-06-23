package com.mutant.sample.nasa.paginglibrary.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mutant.sample.nasa.paginglibrary.model.Apod

@Database(entities = [Apod::class], version = 1, exportSchema = false)
abstract class NasaDatabase : RoomDatabase() {

    abstract fun ApodDao(): ApodDao

    companion object {

        @Volatile
        private var INSTANCE: NasaDatabase? = null

        fun getInstance(context: Context): NasaDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        NasaDatabase::class.java, "Nasa.db")
                        .build()
    }
}