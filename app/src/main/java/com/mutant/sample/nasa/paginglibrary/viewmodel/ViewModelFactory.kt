package com.mutant.sample.nasa.paginglibrary.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mutant.sample.nasa.paginglibrary.model.data.NasaRepository

class ViewModelFactory(private val repository: NasaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}