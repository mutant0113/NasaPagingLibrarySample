package com.mutant.sample.nasa.paginglibrary.model.data

import android.arch.paging.LivePagedListBuilder
import com.mutant.sample.nasa.paginglibrary.model.ApodSearchResult
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.model.api.NasaService
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache

class NasaRepository(private val service: NasaService, private val cache: ApodLocalCache) {

    fun search(queryDate: QueryDate): ApodSearchResult {
        val dataSourceFactory = cache.queryByDate(queryDate)
        val boundaryCallback = ApodBoundaryCallback(queryDate, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        return ApodSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 4
    }
}