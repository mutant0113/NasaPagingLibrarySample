package com.mutant.sample.nasa.paginglibrary.model.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.model.api.NasaService
import com.mutant.sample.nasa.paginglibrary.model.api.searchApods
import com.mutant.sample.nasa.paginglibrary.model.db.ApodLocalCache

class ApodBoundaryCallback(private val queryDate: QueryDate,
                           private val service: NasaService,
                           private val cache: ApodLocalCache) : PagedList.BoundaryCallback<Apod>() {

    // TODO Count date
    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Apod) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if(isRequestInProgress) return

        isRequestInProgress = true
        searchApods(service, queryDate).subscribe {
            isRequestInProgress = false
            if(it.isNotEmpty()) {
                cache.insert(apods = it, insertFinished = {
                    // TODO count date
                })
            }
        }
    }
}