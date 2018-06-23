package com.mutant.sample.nasa.paginglibrary.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class ApodSearchResult(
        private val data: LiveData<PagedList<Apod>>,
        private val networkError: LiveData<String>)