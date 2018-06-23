package com.mutant.sample.nasa.paginglibrary.model.db

import android.arch.paging.DataSource
import com.mutant.sample.nasa.paginglibrary.model.Apod
import java.util.concurrent.Executors

class ApodLocalCache(private val apodDao: ApodDao) {

    fun insert(apods: List<Apod>, insertFinished: () -> Unit) {
        Executors.newSingleThreadExecutor().execute({
            apodDao.insert(apods)
            insertFinished()
        })
    }

    fun queryByDate(dateFrom: String, dateTo: String): DataSource.Factory<Int, Apod> =
            apodDao.queryByDate(dateFrom, dateTo)
}