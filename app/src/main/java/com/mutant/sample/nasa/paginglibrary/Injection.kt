package com.mutant.sample.nasa.paginglibrary

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.mutant.sample.nasa.paginglibrary.model.api.NasaService
import com.mutant.sample.nasa.paginglibrary.model.data.NasaRepository
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache
import com.mutant.sample.nasa.paginglibrary.model.db.NasaDatabase
import com.mutant.sample.nasa.paginglibrary.viewmodel.ViewModelFactory

object Injection {

    /**
     * Creates an instance of [ApodLocalCache] based on the database DAO.
     */
    private fun provideApodCache(context: Context): ApodLocalCache {
        val database = NasaDatabase.getInstance(context)
        return ApodLocalCache(database.ApodDao())
    }

    private fun provideGithubRepository(context: Context): NasaRepository {
        return NasaRepository(NasaService.create(), provideApodCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}