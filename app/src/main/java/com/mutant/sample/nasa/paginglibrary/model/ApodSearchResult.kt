package com.mutant.sample.nasa.paginglibrary.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class ApodSearchResult(val data: LiveData<PagedList<Apod>>,
                            val networkError: LiveData<String>)
