package com.mutant.sample.nasa.paginglibrary

import android.content.Context
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache
import com.mutant.sample.nasa.paginglibrary.model.db.NasaDatabase

object Injection {

    /**
     * Creates an instance of [ApodLocalCache] based on the database DAO.
     */
    private fun provideApodCache(context: Context): ApodLocalCache {
        val database = NasaDatabase.getInstance(context)
        return ApodLocalCache(database.ApodDao())
    }
}