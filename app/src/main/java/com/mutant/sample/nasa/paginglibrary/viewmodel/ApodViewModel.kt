package com.mutant.sample.nasa.paginglibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.mutant.sample.nasa.paginglibrary.DebugUtils
import com.mutant.sample.nasa.paginglibrary.model.Apod
import com.mutant.sample.nasa.paginglibrary.model.ApodSearchResult
import com.mutant.sample.nasa.paginglibrary.model.QueryDate
import com.mutant.sample.nasa.paginglibrary.model.data.NasaRepository

class ApodViewModel(private val repo: NasaRepository) : ViewModel() {

    private val queryDateLiveData = MutableLiveData<QueryDate>()
    var apodResult: LiveData<ApodSearchResult> = Transformations.map(queryDateLiveData, {
        DebugUtils.i("map from queryDateLiveData to apodResult")
        repo.search(it)
    })
    var apods: LiveData<PagedList<Apod>> = Transformations.switchMap(apodResult, {
        DebugUtils.i("switchMap from apodResult to LiveData<PagedList<Apod>>, size:${it.data.value?.size}")
        it.data
    })
    val networkErrors: LiveData<String> = Transformations.switchMap(apodResult, {
        DebugUtils.i("switchMap from apodResult to networkErrors")
        it.networkError
    })

    fun searchApod(queryDate: QueryDate) {
        queryDateLiveData.postValue(queryDate)
    }

    fun lastQueryDate(): QueryDate? {
        return queryDateLiveData.value
    }

}