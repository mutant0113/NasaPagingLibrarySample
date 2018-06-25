package com.mutant.sample.nasa.paginglibrary.model.db

import android.arch.paging.DataSource
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import java.util.concurrent.Executors

class ApodLocalCache(private val apodDao: ApodDao) {

    fun insert(apods: List<Apod>, insertFinished: () -> Unit) {
        Executors.newSingleThreadExecutor().execute({
            apodDao.insert(apods)
            insertFinished()
        })
    }

    fun queryByDate(queryDate: QueryDate): DataSource.Factory<Int, Apod> =
            apodDao.queryByDate(queryDate.startDate, queryDate.endDate)
}