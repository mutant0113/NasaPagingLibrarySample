package com.mutant.sample.nasa.paginglibrary.model.data

import android.arch.paging.PagedList
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.model.api.NasaService
import com.mutant.sample.nasa.paginglibrary.model.api.searchApods
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache

class ApodBoundaryCallback(private val startDate: String,
                           private val endDate: String,
                           private val service: NasaService,
                           private val cache: ApodLocalCache) : PagedList.BoundaryCallback<Apod>() {

    // TODO Count date

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Apod) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        searchApods(service, startDate, endDate).subscribe {
            if(it.apods.isNotEmpty()) {
                cache.insert(apods = it.apods, insertFinished = {
                    // TODO count date
                })
            }
        }
    }
}